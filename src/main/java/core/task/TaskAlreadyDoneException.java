package core.task;

import core.DukeException;

/**
 * Thrown when the user tries to mark a {@code Task} as 'done' when it has already been marked as done.
 */
public class TaskAlreadyDoneException extends DukeException {
    /**
     * Constructor for the class.
     */
    public TaskAlreadyDoneException() {
        super("Task has already been done!");
    }

}
