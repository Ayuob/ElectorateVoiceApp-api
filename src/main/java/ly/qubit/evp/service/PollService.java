package ly.qubit.evp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import ly.qubit.evp.domain.Answer;
import ly.qubit.evp.domain.Poll;
import ly.qubit.evp.domain.Question;
import ly.qubit.evp.repository.AnswerRepository;
import ly.qubit.evp.repository.PollRepository;
import ly.qubit.evp.repository.QuestionRepository;
import ly.qubit.evp.repository.ResponseRepository;
import ly.qubit.evp.service.dto.AnswerResultDTO;
import ly.qubit.evp.service.dto.PollResultDTO;
import ly.qubit.evp.service.dto.QuestionResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollService {

    private final PollRepository pollRepository;

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final ResponseRepository responseRepository;

    public PollService(
        PollRepository pollRepository,
        QuestionRepository questionRepository,
        AnswerRepository answerRepository,
        ResponseRepository responseRepository
    ) {
        this.pollRepository = pollRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.responseRepository = responseRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Transactional
    public Poll savePoll(Poll poll) {
        for (Question question : poll.getQuestions()) {
            question.setPoll(poll);
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
        }
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public List<Poll> getAllVisablePolls() {
        return pollRepository.findByVisibilityTrueOrderByStartDateAsc();
    }

    @Transactional
    public PollResultDTO getPollResults(Long pollId) {
        Optional<Poll> pollOptional = pollRepository.findById(pollId);
        if (pollOptional.isEmpty()) {
            throw new ResourceNotFoundException("Poll not found with id " + pollId);
        }

        Poll poll = pollOptional.get();
        PollResultDTO pollResultDTO = new PollResultDTO();
        pollResultDTO.setPollId(poll.getId());
        pollResultDTO.setTitle(poll.getTitle());

        List<QuestionResultDTO> questionResultDTOs = new ArrayList<>();
        for (Question question : poll.getQuestions()) {
            QuestionResultDTO questionResultDTO = new QuestionResultDTO();
            questionResultDTO.setId(question.getId());
            questionResultDTO.setContent(question.getContent());

            List<AnswerResultDTO> answerResultDTOs = new ArrayList<>();
            for (Answer answer : question.getAnswers()) {
                AnswerResultDTO answerResultDTO = new AnswerResultDTO();
                answerResultDTO.setAnswerId(answer.getId());
                answerResultDTO.setContent(answer.getContent());

                Long responseCount = responseRepository.countByAnswerId(answer.getId());
                answerResultDTO.setResponseCount(responseCount);

                answerResultDTOs.add(answerResultDTO);
            }
            questionResultDTO.setAnswers(answerResultDTOs);
            questionResultDTOs.add(questionResultDTO);
        }
        pollResultDTO.setQuestions(questionResultDTOs);
        return pollResultDTO;
    }

    public Optional<Poll> getPoll(Long pollId) {
        return Optional.ofNullable(pollRepository.findByIdWithQuestions(pollId));
    }
}
