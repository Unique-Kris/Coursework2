package pro.sky.coursework.exception;

import static java.lang.String.format;

public class InvalidQuestionRequestException extends Exception {
    private final int requestedAmount;
    private final int availableAmount;

    public InvalidQuestionRequestException(int requestedAmount, int availableAmount) {
        super(String.format("Requested amount: %d, Available amount: %d", requestedAmount, availableAmount));
        this.requestedAmount = requestedAmount;
        this.availableAmount = availableAmount;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }
}