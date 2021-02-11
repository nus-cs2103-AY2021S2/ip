package duke.exceptions;

import duke.tasks.Task;

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
