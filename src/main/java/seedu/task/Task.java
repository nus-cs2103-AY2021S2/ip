package seedu.task;

// adapted from partial solution on the module webpage

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public int convertNotDoneStatusToOne() {
        return isDone ? 0 : 1;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

