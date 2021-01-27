package duke.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    protected final String taskType;
    protected final String name;
    protected boolean isCompleted;

    public Task(String taskType, String name) {
        this.taskType = taskType;
        this.name = name;
        isCompleted = false;
    }

    public Task(String taskType, String name, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the name of the task.
     *
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the identifier of the type of task.
     *
     * @return identifier of task type
     */
    public abstract String getTaskType();

    /**
     * Checks if the task is already completed.
     *
     * @return true if task is completed, else false
     */
    public boolean isDone() {
        return isCompleted;
    }

    /**
     * Marks a task as completed.
     */
    public void completeTask() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isCompleted ? "X" : " "), name);
    }
}
