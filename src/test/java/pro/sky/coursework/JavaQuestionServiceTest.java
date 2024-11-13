package pro.sky.coursework;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.coursework.exception.QuestionNotFoundException;
import pro.sky.coursework.service.JavaQuestionService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestion_Success() {
        Question question = new Question("What is Java?", "A programming language");
        Question result = questionService.add(question);

        assertEquals(question, result);
        assertEquals(1, questionService.getSize());
    }

    @Test
    void testAddQuestion_Duplicate() {
        Question question = new Question("What is Java?", "A programming language");
        questionService.add(question);

        assertThrows(QuestionNotFoundException.class, () -> {
            questionService.add(question);
        });
    }

    @Test
    void testRemoveQuestion_Success() {
        Question question = new Question("What is Java?", "A programming language");
        questionService.add(question);

        Question result = questionService.remove(question);

        assertEquals(question, result);
        assertEquals(0, questionService.getSize());
    }

    @Test
    void testRemoveQuestion_NotFound() {
        Question question = new Question("What is Java?", "A programming language");

        assertThrows(QuestionNotFoundException.class, () -> {
            questionService.remove(question);
        });
    }

    @Test
    void testGetAllQuestions() {
        Question question1 = new Question("What is Java?", "A programming language");
        Question question2 = new Question("What is Python?", "Another programming language");
        questionService.add(question1);
        questionService.add(question2);

        Collection<Question> questions = questionService.getAll();

        assertEquals(2, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
    }

    @Test
    void testGetRandomQuestion() {
        Question question1 = new Question("What is Java?", "A programming language");
        Question question2 = new Question("What is Python?", "Another programming language");
        questionService.add(question1);
        questionService.add(question2);

        Question randomQuestion = questionService.getRandomQuestion();
        assertTrue(randomQuestion.equals(question1) || randomQuestion.equals(question2));
    }
}