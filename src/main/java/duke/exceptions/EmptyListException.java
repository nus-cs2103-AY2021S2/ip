package duke.exceptions;

/**
 * Represents the exception 'done' or 'delete' command
 * is used but the task list is empty.
 */

public class EmptyListException extends Exception {
    public EmptyListException(String error) {
        super(error);
    }
}
