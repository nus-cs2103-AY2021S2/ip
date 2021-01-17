/**
 * {@code Task} contains an action and its completion status.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new uncompleted  {@code Task}.
     * @param description The name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Toggles to completion status to completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * String representation of the Task.
     * @return Task in check list form.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}