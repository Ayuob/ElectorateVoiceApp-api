package ly.qubit.evp.service.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Value;

public class PollResultDTO implements Serializable {

    private Long pollId;
    private String title;
    private List<QuestionResultDTO> questions;

    // getters and setters

    public Long getPollId() {
        return pollId;
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionResultDTO> getQuestions() {
        return questions;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestions(List<QuestionResultDTO> questions) {
        this.questions = questions;
    }
}
