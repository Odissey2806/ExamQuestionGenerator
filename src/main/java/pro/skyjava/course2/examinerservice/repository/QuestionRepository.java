package pro.skyjava.course2.examinerservice.repository;

import pro.skyjava.course2.examinerservice.domain.Question;
import java.util.Set;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Set<Question> getAll();
}

