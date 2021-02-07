package duke.exceptions;

/**
 * Represents the exception when 'done' or 'delete' command
 * is used but the task list is empty.
 */
public class EmptyListException extends DukeException {
    public EmptyListException(String error) {
        super("\nThe list is empty, Master.");
    }

    @Override
    public String toString() {
        return "Error";
    }
}
