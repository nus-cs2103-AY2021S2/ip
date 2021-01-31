package duke.exceptions;

/**
 * Error class for selecting task index outside of TaskList size.
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructs a CommandNotValidException with specific message
     * for selecting task index outside of TaskList size.
     */
    public TaskNotFoundException() {
        super("Task not in list.");
    }
}
