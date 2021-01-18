/**
 * Task object.
 */
public class Task {
    /**
     * Task description.
     */
    protected String description;
    /**
     * Task completion status.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
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
     *
     * @return status icon. A check mark for complete tasks , an x mark otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
}
