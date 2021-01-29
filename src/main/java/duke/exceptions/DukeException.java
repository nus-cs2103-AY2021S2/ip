package duke.exceptions;

/**
 * <code>DukeException</code> class handles incorrect inputs
 * entered by the user.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
