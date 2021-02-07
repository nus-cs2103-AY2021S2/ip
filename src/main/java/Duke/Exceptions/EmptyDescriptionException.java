package Duke.Exceptions;

/**
 * A subclass of Duke.Duke exception for empty description after a command
 */
public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
