package pro.sky.coursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.coursework.exception.InvalidQuestionRequestException;
import pro.sky.coursework.service.ExaminerServiceImpl;
import pro.sky.coursework.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    private QuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        questionService = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void testGetQuestions_ValidAmount() {
        Set<Question> allQuestions = new HashSet<>();
        allQuestions.add(new Question("What is Java?", "A programming language"));
        allQuestions.add(new Question("What is OOP?", "Object Oriented Programming"));

        when(questionService.getAll()).thenReturn(allQuestions);
        when(questionService.getSize()).thenReturn(allQuestions.size());
        when(questionService.getRandomQuestion()).thenReturn(allQuestions.iterator().next());

        Collection<Question> result = examinerService.getQuestions(1);

        assertEquals(1, result.size());
        assertTrue(allQuestions.containsAll(result));
    }

    @Test
    void testGetQuestions_AmountExceedsAvailableQuestions() {
        when(questionService.getAll()).thenReturn(new HashSet<>());
        when(questionService.getSize()).thenReturn(0);

        InvalidQuestionRequestException exception = assertThrows(InvalidQuestionRequestException.class, () -> {
            examinerService.getQuestions(1);
        });
        assertEquals(1, exception.getRequestedAmount());
        assertEquals(0, exception.getAvailableAmount());
    }

    @Test
    void testGetQuestions_ZeroOrNegativeAmount() {
        assertThrows(InvalidQuestionRequestException.class, () -> examinerService.getQuestions(0));
        assertThrows(InvalidQuestionRequestException.class, () -> examinerService.getQuestions(-1));
    }

    @Test
    void testGetQuestions_ExactAmount() {
        Set<Question> allQuestions = new HashSet<>();
        allQuestions.add(new Question("What is Java?", "A programming language"));
        allQuestions.add(new Question("What is OOP?", "Object Oriented Programming"));

        when(questionService.getAll()).thenReturn(allQuestions);
        when(questionService.getSize()).thenReturn(allQuestions.size());

        Collection<Question> result = examinerService.getQuestions(allQuestions.size());

        assertEquals(allQuestions.size(), result.size());
        assertTrue(allQuestions.containsAll(result));
    }
}