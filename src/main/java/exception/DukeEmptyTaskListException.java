package exception;

/**
 * Duke empty task list exception.
 */
public class DukeEmptyTaskListException extends DukeException {
    /**
     * Instantiates a new Duke empty task list exception.
     */
    public DukeEmptyTaskListException() {
        super(" You have no tasks scheduled at the moment ");
    }
}
