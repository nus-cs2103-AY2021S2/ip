package duke.task;

public abstract class Task {
    protected boolean isDone;
    protected final String description;
    public abstract String encode();

    protected Task(final boolean isDone, final String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
