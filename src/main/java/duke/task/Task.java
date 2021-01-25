package duke.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(final String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public abstract String serialise();

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? '✓' : 'X', description);
    }
}
