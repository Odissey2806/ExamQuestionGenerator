package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import pro.skyjava.course2.examinerservice.domain.Question;
import static org.junit.jupiter.api.Assertions.*;

class MathQuestionServiceTest {
    private final MathQuestionService mathQuestionService = new MathQuestionService();

    @Test
    void getRandomQuestion_ShouldReturnValidMathQuestion() {
        Question question = mathQuestionService.getRandomQuestion();
        assertNotNull(question);
        assertNotNull(question.getQuestion());
        assertNotNull(question.getAnswer());
    }

    @Test
    void add_ShouldThrowException() {
        assertThrows(ResponseStatusException.class,
                () -> mathQuestionService.add("test", "test"));
    }

    @Test
    void remove_ShouldThrowException() {
        assertThrows(ResponseStatusException.class,
                () -> mathQuestionService.remove(new Question("test", "test")));
    }

    @Test
    void getAll_ShouldThrowException() {
        assertThrows(ResponseStatusException.class,
                () -> mathQuestionService.getAll());
    }
}