/**
 * Creates Task object and track its status
 *
 * @author Amanda Ang
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object
     *
     * @param description the description of the Task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task
     *
     * @return "1" if task is completed, "0" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void setDone(boolean hasDone) {
        this.isDone = hasDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " | " + description;
    }
}