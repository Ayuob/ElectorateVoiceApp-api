package ly.qubit.evp.service;

import java.util.List;
import ly.qubit.evp.domain.Candidate;
import ly.qubit.evp.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> searchCandidates(String name, String party) {
        return candidateRepository.findByNameLikeIgnoreCaseAndPartyLikeIgnoreCaseOrderByNameAsc(name, party);
    }
}
