package ly.qubit.evp.repository;

import java.util.List;
import java.util.Optional;
import ly.qubit.evp.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PollRepository extends JpaRepository<Poll, Long> {
    List<Poll> findByVisibilityTrueOrderByStartDateAsc();

    @Query("SELECT p FROM Poll p JOIN FETCH p.questions  WHERE p.id = :id")
    Poll findByIdWithQuestions(@Param("id") Long id);
}
