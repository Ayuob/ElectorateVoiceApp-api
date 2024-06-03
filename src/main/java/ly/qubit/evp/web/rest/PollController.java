package ly.qubit.evp.web.rest;

import java.util.List;
import java.util.Map;
import ly.qubit.evp.domain.Poll;
import ly.qubit.evp.security.AuthoritiesConstants;
import ly.qubit.evp.security.SecurityUtils;
import ly.qubit.evp.service.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = pollService.savePoll(poll);
        Long createdPollId = createdPoll.getId();
        return ResponseEntity.ok(Map.of("status", "success", "message", "Poll created successfully", "pollID", createdPollId));
    }

    @GetMapping
    public ResponseEntity<?> getPolls() {
        if (SecurityUtils.hasCurrentUserThisAuthority(AuthoritiesConstants.ADMIN)) {
            List<Poll> allPolls = pollService.getAllPolls();
            return ResponseEntity.ok(allPolls);
        }
        List<Poll> VisablePolls = pollService.getAllVisablePolls();
        return ResponseEntity.ok(VisablePolls);
    }
}
