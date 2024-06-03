package ly.qubit.evp.repository;

import java.util.List;
import ly.qubit.evp.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByVisibilityTrueOrderByStartDateAsc();
}
