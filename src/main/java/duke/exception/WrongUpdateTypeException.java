package duke.exception;

/**
 * Exception where user inputs an invalid task detail.
 */
public class WrongUpdateTypeException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public WrongUpdateTypeException() {
        super("You have keyed in an invalid instruction.\n"
                + "Please key in details only corresponding to the type of task.");
    }
}
