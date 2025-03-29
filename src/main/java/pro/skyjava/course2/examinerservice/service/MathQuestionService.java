package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Невозможно добавить математический вопрос вручную");
    }

    @Override
    public Question add(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Невозможно добавить математический вопрос вручную");
    }

    @Override
    public Question remove(Question question) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Невозможно удалить математический вопрос");
    }

    @Override
    public Collection<Question> getAll() {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Невозможно получить все математические вопросы");
    }

    @Override
    public Question getRandomQuestion() {
        int a = random.nextInt(50);
        int b = random.nextInt(50) + 1;
        int operation = random.nextInt(4);

        switch(operation) {
            case 0:
                return new Question("Сколько будет " + a + " плюс " + b + "?",
                        "Сумма равна " + (a + b));
            case 1:
                return new Question("Сколько будет " + a + " минус " + b + "?",
                        "Разность равна " + (a - b));
            case 2:
                return new Question("Сколько будет " + a + " умножить на " + b + "?",
                        "Произведение равно " + (a * b));
            case 3:
                return new Question("Сколько будет " + a + " разделить на " + b + "?",
                        "Частное равно " + (a / b));
            default:
                return getRandomQuestion();
        }
    }
}