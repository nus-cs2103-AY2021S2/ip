package duke.exceptions;

public class InvalidUrgencyDaysException extends Exception {

    public InvalidUrgencyDaysException() {
        super("The urgency level should be a positive integer meow!!");
    }
}
