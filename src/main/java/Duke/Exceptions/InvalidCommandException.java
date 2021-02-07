package Duke.Exceptions;

/**
 * A subclass of Duke.Duke exception for an invalid command given
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
