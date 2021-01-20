public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
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

    public void markAsDone() {
        isDone = true;
    }

    public abstract String getType();

    public abstract String toString();
}
