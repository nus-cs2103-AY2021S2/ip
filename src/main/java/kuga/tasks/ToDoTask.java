package kuga.tasks;

/**
 * Represents a normal task.
 */
public class ToDoTask extends Task {
    public static final String IDENTIFIER = "T";

    /**
     * Creates a {@code ToDoTask} object with the given task name,
     * with the task set to initially not completed.
     *
     * @param name name of the task
     */
    public ToDoTask(String name) {
        super(IDENTIFIER, name);
    }

    /**
     * Creates a {@code ToDoTask} object with the given task name,
     * with the task set to the boolean isCompleted.
     *
     * @param name        Name of the task.
     * @param isCompleted Boolean indicating whether the task has been completed.
     */
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
