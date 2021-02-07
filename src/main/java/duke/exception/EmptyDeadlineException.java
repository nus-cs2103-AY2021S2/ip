package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyDeadlineException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDeadlineException() {
        super("The description of a deadline cannot be empty.\n"
                + "Try 'deadline return books /by 12/12/2012 12:00'");
    }
}
