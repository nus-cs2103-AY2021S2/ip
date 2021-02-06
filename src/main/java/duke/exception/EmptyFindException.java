package duke.exception;

/**
 * Exception where user left out the details of the task to delete.
 */
public class EmptyFindException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyFindException() {
        super("The description of a delete cannot be empty.\n"
                + "Try 'delete 1'");
    }
}
