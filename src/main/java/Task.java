public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // done tasks are crossed
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
