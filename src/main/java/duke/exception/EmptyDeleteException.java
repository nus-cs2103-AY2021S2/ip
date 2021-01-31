package duke.exception;

/**
 * Exception where user left out the details of the task to delete.
 */
public class EmptyDeleteException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDeleteException() {
        super("The description of a delete cannot be empty.\n"
                + "Try 'delete 1'");
    }
}
