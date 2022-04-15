package ekud.common.exception;

/**
 * Exception thrown when an invalid command is given.
 */
public class InvalidCommandException extends EkudException {

    public InvalidCommandException() {
        super(Messages.INVALID_COMMAND_EXCEPTION_TEXT);
    }
}
