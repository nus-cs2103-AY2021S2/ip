package ekud.common.exception;

/**
 * Exception thrown when an invalid command is given.
 */
public class InvalidCommandException extends EkudException {
    public InvalidCommandException() {
        super("Huh?! What talking you? I no get you...");
    }
}
