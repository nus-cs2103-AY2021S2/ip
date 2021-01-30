package duke.task;

import duke.exceptions.EmptyTaskDukeException;

public class Todo extends Task {
    public Todo(String input) throws EmptyTaskDukeException {
        super(input);
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[T]" + "[" + taskStringCheck + "] " + super.getTaskName();
    }
}
