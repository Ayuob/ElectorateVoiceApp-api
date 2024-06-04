package ly.qubit.evp.service.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Value;

/**
 * DTO for {@link ly.qubit.evp.domain.Question}
 */

public class QuestionResultDTO implements Serializable {

    private Long id;
    private String content;
    private List<AnswerResultDTO> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AnswerResultDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerResultDTO> answers) {
        this.answers = answers;
    }
}
