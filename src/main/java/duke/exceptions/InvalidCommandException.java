package duke.exceptions;

/**
 * Signals an invalid user command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
