package duke.exception;

/**
 * DukeException extends from Exception class and handles all exception
 * that occurs in this program.
 */
public class DukeException extends Exception {

    /**
     * Returns a DukeException by passing in the cause of the exception.
     *
     * @param message A message describing the cause of this exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
