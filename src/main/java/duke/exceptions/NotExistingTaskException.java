package duke.exceptions;

/**
 * Represents the exception when the 'done' or 'delete' command
 * is used but the task number given is invalid.
 */
public class NotExistingTaskException extends DukeException {
    public NotExistingTaskException() {
        super("NotExistingTaskException");
    }

    @Override
    public String toString() {
        return "I'm afraid the task number is not in the list, Master.";
    }
}
