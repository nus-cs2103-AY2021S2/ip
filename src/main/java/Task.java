/**
 * A {@code Task} contains an action and its completion status.
 */
public class Task {
    private final String description;
    private final boolean isDone;

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a new uncompleted immutable {@code Task}.
     * @param description The name of the task.
     */
    public Task(String description) {
        this(description, false);
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Create a completed version of the current Task.
     * @return The completed version of the current Task.
     */
    public Task markAsDone() {
        return new Task(description, true);
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