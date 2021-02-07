package duke.exceptions;

/**
 * Represents the exception when the 'done' or 'delete' command
 * is used but the task number given is invalid.
 */

public class NotExistingTaskException extends DukeException {
    public NotExistingTaskException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "\nI'm afraid the task number is not in the list, Master.";
    }
}
