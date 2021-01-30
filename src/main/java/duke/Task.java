package duke;

/**
 * Parent class of Todo, Event, Deadline
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String fileFormat() {
        return this.getClass().toString() + " | " + (isDone ? "1 | " : "0 | ") + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markAsDone() {
        return new Task(description, true);
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() { return description; }

    public boolean isDone() {
        return isDone;
    }
}
