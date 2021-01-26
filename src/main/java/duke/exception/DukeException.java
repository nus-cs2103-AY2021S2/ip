package duke.exception;

/**
 * A DukeException class to represents all exceptions with
 * regards to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs DukeException.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
