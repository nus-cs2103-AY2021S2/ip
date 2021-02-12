package duke;

/**
 * Custom Exception class for Duke
 */
public class DukeException extends Exception {
    /**
     * Throws a new DukeException.
     *
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }
}
