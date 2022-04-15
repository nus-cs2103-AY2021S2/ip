package duke.util;

/**
 * Thrown when errors occur in Duke.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super("OOPS!!! " + msg);
    }
}
