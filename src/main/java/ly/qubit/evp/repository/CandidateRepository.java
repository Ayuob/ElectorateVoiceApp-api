package ly.qubit.evp.repository;

import java.util.List;
import ly.qubit.evp.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByNameContainingOrPartyContaining(String name, String party);

    @Query("select c from Candidate c " + "where upper(c.name) like upper(?1) and upper(c.party) like upper(?2) " + "order by c.name")
    List<Candidate> findByNameLikeIgnoreCaseAndPartyLikeIgnoreCaseOrderByNameAsc(@Nullable String name, @Nullable String party);
}
