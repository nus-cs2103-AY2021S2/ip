package duke.exception;

/**
 * Exception where user inputs an invalid task detail.
 */
public class InvalidInstructionException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public InvalidInstructionException() {
        super("You have keyed in an invalid instruction.\n"
                + "Please ensure your instruction begins with\n"
                + "todo, deadline, event, list, delete, done or bye");
    }
}
