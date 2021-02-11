package duke.exceptions;

/**
 * Represents the exception when 'done' or 'delete' command
 * is used but the task list is empty.
 */
public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("EmptyListException");
    }

    @Override
    public String toString() {
        return "I'm afraid the list is empty, Master.";
    }
}
