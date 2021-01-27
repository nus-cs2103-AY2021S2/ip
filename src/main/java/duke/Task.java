package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2718]" : "[ ]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskDetails() {
        return (isDone ? "1" : "0") + " | "
                + description;
    }
}