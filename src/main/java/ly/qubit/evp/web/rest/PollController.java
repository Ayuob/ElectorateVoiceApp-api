package ly.qubit.evp.web.rest;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ly.qubit.evp.domain.Poll;
import ly.qubit.evp.domain.Response;
import ly.qubit.evp.domain.User;
import ly.qubit.evp.security.AuthoritiesConstants;
import ly.qubit.evp.security.SecurityUtils;
import ly.qubit.evp.service.PollService;
import ly.qubit.evp.service.ResponseService;
import ly.qubit.evp.service.UserService;
import ly.qubit.evp.service.dto.PollResultDTO;
import ly.qubit.evp.service.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;

    private final UserService userService;
    private final ResponseService responseService;

    public PollController(PollService pollService, UserService userService, ResponseService responseService) {
        this.pollService = pollService;
        this.userService = userService;
        this.responseService = responseService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = pollService.savePoll(poll);
        Long createdPollId = createdPoll.getId();
        return ResponseEntity.ok(Map.of("status", "success", "message", "Poll created successfully", "pollID", createdPollId));
    }

    @PutMapping("/{pollId}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long pollId, @RequestBody Poll updatedPoll) {
        Poll poll = pollService.updatePoll(pollId, updatedPoll);
        return ResponseEntity.ok(poll);
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        pollService.deletePoll(pollId);
        return ResponseEntity.ok().body("Poll deleted successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllPolls() {
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.ADMIN)) {
            List<Poll> allPolls = pollService.getAllPolls();
            return ResponseEntity.ok(allPolls);
        }
        List<Poll> VisablePolls = pollService.getAllVisablePolls();
        return ResponseEntity.ok(VisablePolls);
    }

    @GetMapping("/{pollId}/results")
    public ResponseEntity<PollResultDTO> getPollResults(@PathVariable Long pollId) {
        PollResultDTO pollResultDTO = pollService.getPollResults(pollId);
        return ResponseEntity.ok(pollResultDTO);
    }

    @PostMapping("/{pollId}/responses/registered")
    public ResponseEntity<?> saveRegisteredResponse(@PathVariable Long pollId, @RequestBody List<ResponseDTO> responseDTOS) {
        // Assuming the Principal object contains the authenticated user information
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }
        String usrLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = userService.getUserWithAuthoritiesByLogin(usrLogin).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        for (ResponseDTO responseDto : responseDTOS) {
            Response response = new Response();
            response.setAnswer(responseService.findAnswerById(responseDto.getAnswerId()));
            response.setUser(user);
            responseService.saveResponse(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Responses submitted successfully");
    }

    @PostMapping("/{pollId}/responses/anonymous")
    public ResponseEntity<?> saveAnonymousResponse(
        @PathVariable Long pollId,
        @RequestBody List<ResponseDTO> responseDTOS,
        HttpServletRequest request
    ) {
        String anonymousUserId = request.getSession(true).getId(); // Get session ID
        for (ResponseDTO responseDto : responseDTOS) {
            Response response = new Response();
            response.setAnswer(responseService.findAnswerById(responseDto.getAnswerId()));
            response.setAnonymousUserId(anonymousUserId); // Set anonymous user ID
            responseService.saveResponse(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Responses submitted successfully");
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        return pollService.getPoll(pollId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
