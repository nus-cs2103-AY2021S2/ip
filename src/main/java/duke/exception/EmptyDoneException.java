package duke.exception;

/**
 * Exception where user left out the details of the task to delete.
 */
public class EmptyDoneException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDoneException() {
        super("The description of a done cannot be empty.\n"
                + "Try 'done 1'");
    }
}
