package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    protected Task(String description, String type) {
        this(description, type, false);
    }

    protected Task(String description, String type, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return " ";
        }
    }

    public String getType() {
        return type;
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String serialize();

    public abstract String toString();
}
