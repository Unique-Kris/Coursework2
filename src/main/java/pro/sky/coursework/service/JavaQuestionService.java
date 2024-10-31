package pro.sky.coursework.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework.Question;
import pro.sky.coursework.exception.QuestionNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question questionResult = new Question(question, answer);
        if (questions.contains(questionResult)) {
            throw new QuestionNotFoundException(question, answer);
        }
        questions.add(questionResult);
        return questionResult;
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionNotFoundException(question.getQuestion(), question.getAnswer());
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question questionResult = new Question(question, answer);
        if (!questions.contains(questionResult)) {
            throw new QuestionNotFoundException(question, answer);
        }
        questions.remove(questionResult);
        return questionResult;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException(question.getQuestion(), question.getAnswer());
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return questions.toArray(new Question[0])[random.nextInt(questions.size())];
    }

    @Override
    public int getSize() {
        return questions.size();
    }
}