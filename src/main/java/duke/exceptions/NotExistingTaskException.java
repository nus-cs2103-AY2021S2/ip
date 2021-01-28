package duke.exceptions;

/**
 * Represents the exception when the 'done' or 'delete' command
 * is used but the task number given is invalid.
 */

public class NotExistingTaskException extends Exception {
    public NotExistingTaskException(String error) {
        super(error);
    }
}
