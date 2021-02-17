package duke.exception;

/**
 * The DukeException class is a wrapper for checked exceptions
 * encountered when an instance of Duke is running.
 */
public class DukeException extends Exception {
    /**
     * Constructor takes in the message to be shown to the user.
     * @param message The message to be shown to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
