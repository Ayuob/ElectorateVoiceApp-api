package ly.qubit.evp.web.rest;

import java.util.List;
import java.util.Map;
import ly.qubit.evp.domain.Candidate;
import ly.qubit.evp.service.CandidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<?> searchCandidates(@RequestParam(required = false) String name, @RequestParam(required = false) String party) {
        List<Candidate> candidates = candidateService.searchCandidates(name, party);
        return ResponseEntity.ok(Map.of("candidates", candidates));
    }
}
