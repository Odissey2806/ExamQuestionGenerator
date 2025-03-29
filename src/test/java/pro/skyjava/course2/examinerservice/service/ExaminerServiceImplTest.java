package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.InvalidAmountException;

import java.util.Collection;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_ShouldReturnCorrectNumberOfQuestions() {
        Question javaQuestion = new Question("Что такое Java?", "Язык программирования");
        Question mathQuestion = new Question("2 + 2", "4");

        when(javaQuestionService.getRandomQuestion()).thenReturn(javaQuestion);
        when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestion);

        Collection<Question> result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        assertTrue(result.contains(javaQuestion));
        assertTrue(result.contains(mathQuestion));
    }

    @Test
    void getQuestions_ShouldThrowExceptionWhenInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> {
            examinerService.getQuestions(0);
        });
    }
}