package duke;

/**
 * The DukeException class is a wrapper for checked exceptions
 * encountered when an instance of Duke is running.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
