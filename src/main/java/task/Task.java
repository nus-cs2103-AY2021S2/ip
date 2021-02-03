package task;

import misc.Color;

/**
 * task.Task object.
 */
public class Task {
    /**
     * task.Task description.
     */
    protected String description;
    /**
     * task.Task completion status.
     */
    protected boolean isDone;

    /**
     * Instantiates a new task.Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves status string.
     * r
     *
     * @return status icon. A check mark for complete tasks , an x mark otherwise.
     */
    public String getStatusIcon() {
        return (isDone
                ? "\u2713"
                : "\u2718");
    }

    /**
     * Gets task description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description + " " + getStatusIcon();
    }
}
