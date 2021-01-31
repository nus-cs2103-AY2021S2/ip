package duke.exception;

/**
 * Exception where user left out the details of the task.
 */
public class EmptyEventException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyEventException() {
        super("The description of an event cannot be empty.\n"
                + "Try 'event pick the kids /at 12/12/2012 12:00'");
    }
}
