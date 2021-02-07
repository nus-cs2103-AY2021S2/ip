package Duke.Exceptions;

/**
 * A subclass of Duke.Duke exception for an invalid description given
 */
public class InvalidDescriptionException extends DukeException {

    public InvalidDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
