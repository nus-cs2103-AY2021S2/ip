package duke.tasks;

/**
 * Represents a task.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets task status icon.
     * @return task status icon
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns identifier of type of task.
     * @return identifier of task type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns description of task.
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if task is completed.
     * @return true if task is completed, false otherwise
     */
    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
