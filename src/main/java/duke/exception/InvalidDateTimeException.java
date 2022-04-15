package duke.exception;

/**
 * A class represents an InvalidDateTimeException.
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Constructs an InvalidDateTimeException.
     * @param message The error message.
     */
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
