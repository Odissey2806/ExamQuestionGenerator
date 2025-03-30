package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.repository.JavaQuestionRepository;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository repository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void addQuestion_ShouldReturnAddedQuestion() {
        Question question = new Question("Что такое Java?", "Язык программирования");
        when(repository.add(any(Question.class))).thenReturn(question);

        Question result = javaQuestionService.add(question.getQuestion(), question.getAnswer());

        assertEquals(question, result);
        verify(repository, times(1)).add(any(Question.class));
    }

    @Test
    void getRandomQuestion_WhenNoQuestions_ShouldThrowException() {
        when(repository.getAll()).thenReturn(Collections.emptySet());

        assertThrows(IllegalStateException.class,
                () -> javaQuestionService.getRandomQuestion());
    }
}

