package core.task;

import core.DukeException;

public class TaskAlreadyDoneException extends DukeException {
    public TaskAlreadyDoneException() {
        super("Task has already been done!");
    }

}
