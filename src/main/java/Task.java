public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String statusIcon;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.statusIcon = "[ ] ";
    }

    public void markAsDone() {
        this.isDone = true;
        this.statusIcon = "[\u2713] ";

    }

    public String getStatusIcon() {
        return statusIcon;
    }

    public String toString() {
        return statusIcon + this.taskName;
    }
}
