package duke.tasks;

public abstract class Task {
    protected final String description;
    protected final String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }
}
