package pason.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns done status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the Task int he file format.
     */
    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    public String toString() {
        return (this.isDone ? "[\u2713]" : "[\u2718]") + " " + this.description;
    }
}
