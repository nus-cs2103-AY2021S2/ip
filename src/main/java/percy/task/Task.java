package percy.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs Task.
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs Task with indication of completion
     * @param description description of task.
     * @param isDone the completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark the task as done.
     */
    public void doTask() {
        this.isDone = true;
    }

    /**
     * Get the completion status number for storage purposes.
     * 1 for done, 0 for not done.
     * @return 1 or 0.
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    /**
     * Get the status icon.
     * Tick for completed task.
     * @return tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2717");
    }

    /**
     * Get the description of task.
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Formats task to be suitable for storage.
     * @return string for storage.
     */
    public String formatToFile() {
        return this.getStatusNumber() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

