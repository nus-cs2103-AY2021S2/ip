package duke;

/**
 * Represents a Task object.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // tasks always start as not done
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getSaveString();

    /**
     * Returns a String representation of whether this Task is done.
     * @return "X" if the Task is done, " " (one space) otherwise.
     */
    protected String getStatus() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }
}
