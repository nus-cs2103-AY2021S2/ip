package duke;

/**
 * Represent errors encountered during execution of Duke.
 */
public class DukeException extends Exception {
    /**
     * Construct a DukeException with a specified message
     * @param message the specified message
     */
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
