package duke.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    protected Task(final String desc) {
        this.description = desc;
        this.isDone = false;
    }

    /**
     * Serialise this task into a format that can be saved.
     * Implementation dependent.
     * @return a serialised version of the task, implementation dependent.
     */
    public abstract String serialise();

    /**
     * Marks this task as done.
     */
    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? '✓' : 'X', description);
    }
}
