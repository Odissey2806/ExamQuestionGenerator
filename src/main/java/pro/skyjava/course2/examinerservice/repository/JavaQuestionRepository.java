package pro.skyjava.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import pro.skyjava.course2.examinerservice.domain.Question;

import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        return null;
    }

    public Set<Question> getAll() {
        return new HashSet<>(questions);
    }
}

