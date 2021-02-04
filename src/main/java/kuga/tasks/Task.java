package kuga.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    protected final String taskType;
    protected final String name;
    protected boolean isCompleted;

    /**
     * Constructor to create a {@code Task} object with the given task type and task name,
     * with the task set to initially not completed.
     *
     * @param taskType Identifier of the task type.
     * @param name     Name of the task.
     */
    public Task(String taskType, String name) {
        this.taskType = taskType;
        this.name = name;
        isCompleted = false;
    }

    /**
     * Constructor to create a {@code Task} object with the given task type and task name,
     * with the task set to the boolean isCompleted.
     *
     * @param taskType    Identifier of the task type.
     * @param name        Name of the task.
     * @param isCompleted Boolean indicating whether the task has been completed.
     */
    public Task(String taskType, String name, boolean isCompleted) {
        this.taskType = taskType;
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the identifier of the type of task.
     *
     * @return Identifier of task type.
     */
    public abstract String getTaskType();

    /**
     * Checks if the task is already completed.
     *
     * @return True if task is completed, else false.
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
