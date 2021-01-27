package duke.exceptions;

/**
 * Signals an invalid user argument.
 */
public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
