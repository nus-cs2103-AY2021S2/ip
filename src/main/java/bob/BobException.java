package bob;

/**
 * Thrown to indicate an error in Duke
 */
public class BobException extends Exception {
    public BobException(String errorMessage) {
        super(errorMessage);
    }

    public BobException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
