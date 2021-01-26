package duke.task;

public abstract class Task {
    private final String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        isDone = false;
    }

    protected Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
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

    public abstract String getSerialized();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getContent());
    }
}
