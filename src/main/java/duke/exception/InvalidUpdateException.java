package duke.exception;

/**
 * Exception where user inputs an invalid update task.
 */
public class InvalidUpdateException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public InvalidUpdateException() {
        super("You have keyed in an invalid instruction.\n"
                + "Try 'update 1 details /to do some work'");
    }
}
