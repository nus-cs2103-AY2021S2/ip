package duke.task;

/**
 * Base Task class.
 */
public abstract class Task implements Comparable<Task> {
    private static int nextId = 0;

    protected int id;
    protected final String content;
    protected boolean isDone;

    public Task(String content) {
        id = nextId;
        nextId++;

        this.content = content;
        isDone = false;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task other = (Task) o;
        return id == other.id && content.equals(other.content) && isDone == other.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}
