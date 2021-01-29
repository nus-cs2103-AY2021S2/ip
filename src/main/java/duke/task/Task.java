package duke.task;

/**
 * Represents a task in the Duke chat bot.
 * Typically used as a template for
 * more specific tasks to inherit.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initialises the task with a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return Task status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "*" : " ");
    }

    /**
     * Sets the status of the task as 'done'.
     * If the task is already marked as done,
     * no action will be taken.
     *
     * @return Outcome of the action.
     */
    public Boolean markAsDone() {
        if (this.isDone)
            return false;
        this.isDone = true;
        return true;
    }

    /**
     * Formats the task string representation.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
