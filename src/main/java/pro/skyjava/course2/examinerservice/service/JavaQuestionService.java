package pro.skyjava.course2.examinerservice.service;

import pro.skyjava.course2.examinerservice.domain.Question;
import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.repository.JavaQuestionRepository;

import java.util.*;
import java.util.Collection;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {
    private final JavaQuestionRepository repository;
    private final Random random = new Random();

    public JavaQuestionService(JavaQuestionRepository repository) {
        this.repository = repository;
        initializeTestQuestions();
    }

    private void initializeTestQuestions() {
        add("Что такое Java?", "Язык программирования");
        add("Что такое JVM?", "Виртуальная машина Java");
        add("Что такое ООП?", "Объектно-ориентированное программирование");
        add("Как объявить класс в Java?", "class MyClass {}");
        add("Что такое interface?", "Абстрактный тип, задающий поведение");
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        repository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        repository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> all = repository.getAll();
        if (all.isEmpty()) {
            throw new IllegalStateException("Нет доступных вопросов");
        }
        return new ArrayList<>(all).get(random.nextInt(all.size()));
    }
}

