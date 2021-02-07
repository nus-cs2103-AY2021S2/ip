package duke.task;

/**
 * Base Task class.
 */
public abstract class Task implements Comparable<Task> {
    private final String content;
    private boolean isDone;
    private static int NEXT_ID = 0;
    private int id;

    public Task(String content) {
        this.content = content;
        isDone = false;
        id = NEXT_ID;
        NEXT_ID++;
    }

    protected Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public String getContent() {
        return content;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    public abstract String getSerialized();

    public abstract boolean hasStrInProps(String str);

    @Override
    public int compareTo(Task other) {
        return Integer.compare(id, other.id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}
