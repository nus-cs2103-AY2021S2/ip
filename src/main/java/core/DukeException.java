package core;

/**
 * Base class for all types of exceptions to be thrown inside Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Creates a new exception without any error message.
     */
    public DukeException() {
    }

    /**
     * Creates a new exception with an error message.
     * @param s - error message.
     */
    public DukeException(String s) {
        super(s);
    }

}
