package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task with its description
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return If the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an icon representing the completion status of the task.
     * If the task is done, a cross is returned.
     *
     * @return An status icon representing the task status.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts the task to a string which will be saved in a file.
     *
     * @return String representing the task in its save format.
     */
    public String toSaveFormat() {
        return getStatusIcon() + " | " + this.description;
    }

    /**
     * Converts the task to a string.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
