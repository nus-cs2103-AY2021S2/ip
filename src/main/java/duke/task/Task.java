package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toSaveFormat() {
        throw new UnsupportedOperationException("Method must be implemented by child classes");
    };

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
