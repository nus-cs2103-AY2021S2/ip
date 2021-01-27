package duke.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    protected final String description;
    protected final String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets task status icon.
     * @return task status icon
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns identifier of type of task.
     * @return identifier of task type
     */
    public String getType() {
        return type;
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
        return isDone;
    }

    public boolean contains(String keyword) {
        String[] descriptionArray = this.description.split(" ");
        for (String s : descriptionArray) {
            if (keyword.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
