package duke.exceptions;

/**
 * Represents the a command is used but task name is
 * not being inputted
 */
public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException() {
        super("\nMaster, I'm afraid you are not specific enough.");
    }

    @Override
    public String toString() {
        return "Error";
    }
}
