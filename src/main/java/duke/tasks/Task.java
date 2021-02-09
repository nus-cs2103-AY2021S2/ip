package duke.tasks;

/**
 * Abstract class for all tasks
 */
public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;

    /**
     * Task constructor
     *
     * @param taskName Name of task
     * @param isCompleted Completion status of task
     */
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Gets status
     * @return Status
     */
    public String getStatus() {
        return isCompleted ? "\u2713" : "\u2717";
    }

    /**
     * Gets task name
     * @return Task name
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Marks the task as completed
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Abstract method for generating string to be stored in local disk
     */
    public abstract String toStorageString();
    public abstract String getTime();
    public void editTime(String newTime) {};
    public void editName(String newName) {
        this.taskName = newName;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.taskName;
    }
}
