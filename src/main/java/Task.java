/**
 * A task with a description and a status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with a given description.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark task as done and return copy with updated state.
     * @return Copy of task with the state updated to done.
     */
    public Task markAsDone() {
        return new Task(this.description, true);
    }

    @Override
    public String toString() {
        String taskFormat = "[%s] %s";
        return String.format(taskFormat, this.getStatusIcon(), this.description);
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
