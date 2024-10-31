package pro.sky.coursework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.coursework.Question;
import pro.sky.coursework.service.ExaminerService;
import pro.sky.coursework.service.ExaminerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping
public class ExamController {

    private static final Logger log = LoggerFactory.getLogger(ExaminerServiceImpl.class);

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public ResponseEntity<Collection<Question>> getQuestions(@PathVariable int amount) {
        try {
            Collection<Question> questions = examinerService.getQuestions(amount);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}