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

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    public String toString() {
        return (this.isDone ? "[\u2713]" : "[\u2718]") + " " + this.description;
    }
}
