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
                + "Type 'help' to get the full list of available commands.");
    }
}
