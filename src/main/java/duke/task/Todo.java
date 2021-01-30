package duke.task;

import duke.EmptyTaskDukeException;

public class Todo extends Task {
    public Todo(String input) throws EmptyTaskDukeException {
        super(input);
    }

    @Override
    public String setDone() {
        super.setTaskCompleted();
        return "Nice! I've marked this todo as done:\n"
                + toString();
    }

    @Override
    public String toString() {
        String taskStringCheck = super.getIsTaskCompleted() ? "X" : " ";
        return "[T]" + "[" + taskStringCheck + "] " + super.getTaskName();
    }
}
