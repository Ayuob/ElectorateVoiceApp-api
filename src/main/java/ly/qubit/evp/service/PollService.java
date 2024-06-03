package ly.qubit.evp.service;

import java.util.List;
import lombok.Setter;
import ly.qubit.evp.domain.Answer;
import ly.qubit.evp.domain.Poll;
import ly.qubit.evp.domain.Question;
import ly.qubit.evp.repository.PollRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
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
}
