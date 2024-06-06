package ly.qubit.evp.web.rest;

import ly.qubit.evp.domain.Answer;
import ly.qubit.evp.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long answerId, @RequestBody Answer updatedAnswer) {
        Answer answer = answerService.updateAnswer(answerId, updatedAnswer);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.ok().body("Answer deleted successfully");
    }
}
