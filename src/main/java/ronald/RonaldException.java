package ronald;

/**
 * Custom Exception class for Duke
 */
public class RonaldException extends Exception {
    /**
     * Throws a new DukeException.
     *
     * @param message error message
     */
    public RonaldException(String message) {
        super(message);
    }
}
