package ly.qubit.evp.service;

import ly.qubit.evp.domain.Answer;
import ly.qubit.evp.domain.Response;
import ly.qubit.evp.repository.AnswerRepository;
import ly.qubit.evp.repository.ResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final AnswerRepository answerRepository;

    public ResponseService(ResponseRepository responseRepository, AnswerRepository answerRepository) {
        this.responseRepository = responseRepository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    public Answer findAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found"));
    }
}
