package task;

import misc.Color;

/**
 * task.Task object.
 */
public class Task {
    /**
     * task.Task description.
     */
    public String description;
    /**
     * task.Task completion status.
     */
    public boolean isDone;

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
     *r
     * @return status icon. A check mark for complete tasks , an x mark otherwise.
     */
    public String getStatusIcon() {
        return (isDone
                ? Color.GREEN_BOLD + "\u2713" + Color.RESET
                : Color.RED_BOLD + "\u2718" + Color.RESET);
    }

    @Override
    public String toString() {
        return this.description + " " + getStatusIcon();
    }
}
