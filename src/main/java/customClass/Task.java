package customClass;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean toggleIsDone() {
        isDone = !isDone;
        return isDone;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
