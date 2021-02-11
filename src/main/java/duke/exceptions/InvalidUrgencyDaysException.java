package duke.exceptions;

public class InvalidUrgencyDaysException extends Exception {

    public InvalidUrgencyDaysException() {
        super("You need to input a positive integer to specify the urgency level :O");
    }
}
