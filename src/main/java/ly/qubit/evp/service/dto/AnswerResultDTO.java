package ly.qubit.evp.service.dto;

import java.io.Serializable;
import lombok.Value;

/**
 * DTO for {@link ly.qubit.evp.domain.Answer}
 */

public class AnswerResultDTO implements Serializable {

    private Long answerId;
    private String content;
    private Long responseCount;

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResponseCount(Long responseCount) {
        this.responseCount = responseCount;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getContent() {
        return content;
    }

    public Long getResponseCount() {
        return responseCount;
    }
}
