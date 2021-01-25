package duke.tasks;

import duke.tasks.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        this(description, false);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + ((isDone) ? 1 : 0) + " | " + description;
    }
}
