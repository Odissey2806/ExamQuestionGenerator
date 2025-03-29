package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.InvalidAmountException;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Collection<QuestionService> questionServices;
    private final Random random = new Random();

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        this.questionServices = List.of(javaQuestionService, mathQuestionService);
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Запрошенное количество вопросов должно быть положительным числом");
        }

        int totalAvailable = questionServices.stream()
                .mapToInt(service -> {
                    try {
                        return service.getAll().size();
                    } catch (Exception e) {
                        return Integer.MAX_VALUE;
                    }
                })
                .sum();

        if (amount > totalAvailable) {
            throw new InvalidAmountException("Запрошено слишком много вопросов. Максимально доступно: " + totalAvailable);
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            QuestionService service = getRandomService();
            try {
                Question question = service.getRandomQuestion();
                if (question != null) {
                    questions.add(question);
                }
            } catch (Exception e) {
                System.out.println("Ошибка при получении вопроса из сервиса: " + e.getMessage());
            }
        }
        return questions;
    }

    private QuestionService getRandomService() {
        return questionServices.stream()
                .skip(random.nextInt(questionServices.size()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Нет доступных сервисов вопросов"));
    }
}
