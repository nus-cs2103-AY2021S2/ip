package duke.tasks;

public abstract class Task {
    protected String taskName;
    protected boolean isCompleted;

    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getStatus() {
        return isCompleted ? "\u2713" : "\u2717";
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public abstract String toStorageString();

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.taskName;
    }
}
