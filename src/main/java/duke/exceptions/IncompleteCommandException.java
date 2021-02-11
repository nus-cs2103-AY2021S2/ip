package duke.exceptions;

/**
 * Represents the a command is used but task name is
 * not being inputted
 */
public class IncompleteCommandException extends DukeException {
    public IncompleteCommandException() {
        super("IncompleteCommandException");
    }

    @Override
    public String toString() {
        return "Master, I'm afraid you are not specific enough.";
    }
}
