package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(int done, String description) {
        this.isDone = done == 1 ? true : false;
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "\u2718" : " ";
    }

    public void setDone() {
        isDone = true;
    }

    public boolean getDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public abstract String toStorageString();
}