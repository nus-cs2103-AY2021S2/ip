package tasks;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String statusIcon;
    protected String isDoneString;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.statusIcon = "[ ] ";
        this.isDoneString = "0 / ";
    }

    public void markAsDone() {
        this.isDone = true;
        this.statusIcon = "[\u2713] ";
        this.isDoneString = "1 / ";

    }

    public String getStatusIcon() {
        return statusIcon;
    }

    public String toSave() {
        return this.taskName;
    }

    public String toString() {
        return statusIcon + this.taskName;
    }
}
