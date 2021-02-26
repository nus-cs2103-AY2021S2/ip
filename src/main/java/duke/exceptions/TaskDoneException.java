package duke.exceptions;

import duke.tasks.Task;

/**
 * Represents the exception when a task has been marked as completed
 * but the 'done' command is called on the task again.
 */
public class TaskDoneException extends DukeException {
    private Task task;

    public TaskDoneException(Task task) {
        super ("TaskDoneException");
        this.task = task;
    }

    @Override
    public String toString() {
        return "Master, "
                + this.task.toString()
                + " has already been marked as completed.";
    }
}
