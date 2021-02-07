package duke.exceptions;

/**
 * Represents the exception 'done' or 'delete' command
 * is used but the task list is empty.
 */

public class EmptyListException extends DukeException {
    public EmptyListException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "\nThe list is empty, Master.";
    }
}
