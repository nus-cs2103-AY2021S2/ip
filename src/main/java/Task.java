

public class Task {
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    public void markDone() {
        this.isDone = !this.isDone;
    }

    String description;
    boolean isDone;

    @Override
    public String toString() {
        String doneStatus = "[" + getStatusIcon() + "]";
        return doneStatus + " " + this.description;
    }
}
