package ly.qubit.evp.repository;

import ly.qubit.evp.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Transactional
    void deleteByQuestionId(Long questionId);

    @Override
    @Transactional
    void deleteById(Long aLong);

    @Transactional
    @Modifying
    @Query("delete from Answer a where a.id = ?1")
    void deleteByIdQuery(Long id);
}
