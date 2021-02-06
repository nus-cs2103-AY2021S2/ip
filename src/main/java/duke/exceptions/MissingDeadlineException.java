package duke.exceptions;

public class MissingDeadlineException extends Exception {

    public MissingDeadlineException() {
        super("You need to specify a deadline following a '/by' marker...");
    }
}
