package pro.sky.coursework.exception;

public class QuestionNotFoundException extends RuntimeException {
    private final String question;
    private final String answer;

    public QuestionNotFoundException(String question, String answer) {
        super(String.format("Question '%s' with answer '%s' not found", question, answer));
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}