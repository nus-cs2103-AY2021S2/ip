package duke.exceptions;

/**
 * <code>MissingTaskNumberException</code> handles incorrect user inputs
 * with missing task number.
 */
public class MissingTaskNumberException extends DukeException {

    /**
     * Constructor for MissingTaskNumberException.
     */
    public MissingTaskNumberException() {
        super("     Error!! Please enter a task number and try again! :)");
    }
}
