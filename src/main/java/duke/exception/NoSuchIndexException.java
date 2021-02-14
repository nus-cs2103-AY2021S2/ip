package duke.exception;

/**
 * Exception where user inputs an invalid update task.
 */
public class NoSuchIndexException extends DukeException {

    /**
     * Method to throw the Exception.
     */
    public NoSuchIndexException() {
        super("You have keyed in an invalid index.\n"
                + "Add an item to that index to update it!");
    }
}

