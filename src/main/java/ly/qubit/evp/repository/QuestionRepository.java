package ly.qubit.evp.repository;

import ly.qubit.evp.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
