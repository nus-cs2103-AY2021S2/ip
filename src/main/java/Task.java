public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
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
}
