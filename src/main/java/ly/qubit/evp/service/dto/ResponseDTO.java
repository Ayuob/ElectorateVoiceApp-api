package ly.qubit.evp.service.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private Long questionId;
    private Long answerId;
    private String anonymousUserId; // Optional for anonymous responses

    // You can add more fields if needed

    // Getters and Setters

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnonymousUserId() {
        return anonymousUserId;
    }

    public void setAnonymousUserId(String anonymousUserId) {
        this.anonymousUserId = anonymousUserId;
    }
}
