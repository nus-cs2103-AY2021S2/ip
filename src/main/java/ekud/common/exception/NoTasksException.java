package ekud.common.exception;

/**
 * Exception to be thrown when there are no known tasks.
 */
public class NoTasksException extends EkudException {
    public NoTasksException() {
        super("Hmm, you got nothing better to do.");
    }
}
