package pro.sky.coursework.service;

import pro.sky.coursework.Question;
import pro.sky.coursework.exception.InvalidQuestionRequestException;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount) throws InvalidQuestionRequestException;
}