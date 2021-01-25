package duke.task;

public abstract class Task {

    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[\u2713] " : "[ ] ";
    }

    String getDate() {
        return "";
    }

    public void markDone() {
        isDone = true;
    }

    public abstract String getFormattedString();

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
