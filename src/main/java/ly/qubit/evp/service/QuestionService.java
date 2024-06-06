package ly.qubit.evp.service;

import ly.qubit.evp.domain.Question;
import ly.qubit.evp.repository.AnswerRepository;
import ly.qubit.evp.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        question.setContent(updatedQuestion.getContent());
        return questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        // Delete all associated answers first
        answerRepository.deleteByQuestionId(questionId);
        questionRepository.delete(question);
    }
}
