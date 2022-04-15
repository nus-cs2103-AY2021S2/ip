package duke.exceptions;

/**
 * Represents an exception thrown by duke when an error occurs.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param s Message of the Exception.
     */
    public DukeException(String s) {
        super(s);
    }

    /**
     * Constructor for DukeException.
     */
    public DukeException() {
        super();
    }
}
