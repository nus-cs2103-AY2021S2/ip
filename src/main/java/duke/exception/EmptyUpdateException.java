package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyUpdateException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyUpdateException() {
        super("The description of an update cannot be empty.\n"
                + "Try 'update 1 details /to do some work'");
    }
}
