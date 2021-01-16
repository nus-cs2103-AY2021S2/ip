public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2718] " : "[ ] "); //return tick or X symbols
    }

    public String toString() {
        return this.taskName;
    }
}
