public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    protected Task(String description, String type) {
        this.description = description;
        this.isDone = false;
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
