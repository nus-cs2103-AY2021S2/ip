package duke.exceptions;

/**
 * <code>InvalidTaskNumberException</code> handles incorrect user inputs invalid task number entered.
 */
public class InvalidTaskNumberException extends DukeException {

    /**
     * Constructor for InvalidTaskNumberException class.
     */
    public InvalidTaskNumberException() {
        super ("☹ OOPS!!! You have entered an invalid task number. ☹");
    }
}
