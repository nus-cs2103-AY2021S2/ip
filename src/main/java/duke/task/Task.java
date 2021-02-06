package duke.task;

/**
 * Task represents a task that is to be done.
 */
public abstract class Task {
    /**
     * Description of task.
     */
    protected String description;
    /**
     * Status of task.
     */
    protected boolean isDone;

    /**
     * Creates new instance of a task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon depending on status of task.
     *
     * @return status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns String representation of task to be saved to save file.
     *
     * @return String representation of task.
     */
    public abstract String serialise();

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
