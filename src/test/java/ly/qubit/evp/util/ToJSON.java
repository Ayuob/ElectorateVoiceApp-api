package ly.qubit.evp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import ly.qubit.evp.domain.Answer;
import ly.qubit.evp.domain.Poll;
import ly.qubit.evp.domain.Question;
import ly.qubit.evp.domain.User;
import org.junit.jupiter.api.Test;

public class ToJSON {

    @Test
    public void testCreateUser() throws Exception {
        // Create a User object
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setCreatedDate(Instant.now());

        Answer answer = new Answer();
        answer.setId(1L);
        answer.setContent("test");

        Question question = new Question();
        question.setId(1L);
        question.setContent("test");
        question.setPoll(null);
        question.setAnswers(List.of(answer));

        Poll poll = new Poll();
        poll.setId(1L);
        poll.setTitle("test");
        poll.setDescription("test");
        poll.setStartDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        poll.setEndDate(LocalDateTime.ofInstant(Instant.now().plus((Duration.ofDays(7))), ZoneId.systemDefault()));
        poll.setVisibility(true);
        poll.setQuestions(List.of(question));

        // Convert the User object to JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // register time module
        objectMapper.registerModule(new JavaTimeModule());

        String userJson = objectMapper.writeValueAsString(poll);

        // Print JSON to verify
        System.out.println(userJson);
        // Test the REST API using Rest Assured

    }
}
