package duke.exceptions;

/**
 * Error class for all exceptions thrown in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified message.
     *
     * @param message Message of exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
