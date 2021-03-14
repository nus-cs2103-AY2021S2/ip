package exception;

/**
 * duke.Duke missing arguments exception.
 */
public class DukeMissingArgumentsException extends DukeException {
    /**
     * Instantiates a missing arguments exception.
     */
    public DukeMissingArgumentsException() {
        super("Oops! One or more arguments are not valid, please try again");
    }
}
