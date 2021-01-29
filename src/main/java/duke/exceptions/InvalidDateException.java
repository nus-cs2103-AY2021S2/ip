package duke.exceptions;

/**
 * <code>InvalidDateException</code> handles incorrect user inputs with invalid date entered.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructor for InvalidDateException class.
     */
    public InvalidDateException() {
        super ("     ☹ OOPS!!! You have entered an invalid date/time.");
    }
}
