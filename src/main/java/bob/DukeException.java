package bob;

/**
 * Thrown to indicate an error in Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
