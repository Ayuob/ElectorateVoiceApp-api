package ly.qubit.evp.service;

import ly.qubit.evp.domain.Answer;
import ly.qubit.evp.repository.AnswerRepository;
import ly.qubit.evp.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public Answer updateAnswer(Long answerId, Answer updatedAnswer) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        answer.setContent(updatedAnswer.getContent());
        return answerRepository.save(answer);
    }

    @Transactional
    public void deleteAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
        answerRepository.deleteByIdQuery(answerId);
    }

    public Answer findAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
    }
}
