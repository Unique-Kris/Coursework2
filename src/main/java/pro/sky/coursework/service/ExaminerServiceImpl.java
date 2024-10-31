package pro.sky.coursework.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import pro.sky.coursework.Question;
import pro.sky.coursework.exception.InvalidQuestionRequestException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private static final Log log = LogFactory.getLog(ExaminerServiceImpl.class);

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();
        int size = questionService.getSize();
        if (amount <= 0 || amount > allQuestions.size()) {
            log.error("Wrong requested amount, should be less then" + size);
            throw new InvalidQuestionRequestException(amount, size);
        }
        if (amount == allQuestions.size()) {
            return allQuestions;
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        log.info("Questions were retrieved");
        return questions;
    }
}