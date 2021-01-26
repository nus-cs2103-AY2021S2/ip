package duke.task;

public class Task {
    private final String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
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

    public String getSerialized() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns true if the task contains str in one of its fields
     *
     * @param str the target string
     * @return true if the task contains str in one of its fields
     */
    public boolean hasStrInProps(String str) { throw new UnsupportedOperationException(); }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}
