package duke.exceptions;

import duke.tasks.Task;

public class TaskDoneException extends DukeException {
    public TaskDoneException(Task task) {
        super ("\nMaster, "
                + task.toString()
                + " has already been marked as completed.");
    }
}
