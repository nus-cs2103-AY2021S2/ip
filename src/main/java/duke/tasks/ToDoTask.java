package duke.tasks;

import duke.tasks.Task;

public class ToDoTask extends Task {
    /**
     * Creates a ToDoTask object.
     * @param description
     * @param isDone
     */
    public ToDoTask(String description, boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getTaskDetails() {
        String divider = " | ";
        return "T" + divider
                + (isDone ? "1" : "0") + divider
                + description;
    }
}
