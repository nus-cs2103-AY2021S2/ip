package duke.exception;

/**
 * Exception where user left out the date or time of the task.
 */
public class EmptyDateTimeException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public EmptyDateTimeException() {
        super("Please key in a valid date and/or time in the format\n "
                + "dd/MM/yyyy HH:mm.");
    }
}
