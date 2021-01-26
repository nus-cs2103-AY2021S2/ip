/**
 * A subclass of Duke exception for an invalid command given
 */
public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
