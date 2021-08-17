package bearbear.exceptions;

/**
 * Signals an invalid user command.
 */
public class InvalidCommandException extends BearBearException {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
