package duke.tasks;

/**
 * Represents a normal task.
 */
public class ToDoTask extends Task {
    public static final String IDENTIFIER = "T";

    public ToDoTask(String name) {
        super(IDENTIFIER, name);
    }

    public ToDoTask(String name, boolean isCompleted) {
        super(IDENTIFIER, name, isCompleted);
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
