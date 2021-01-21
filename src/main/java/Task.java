

public class Task {
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    public void markDone() {
        this.isDone = !this.isDone;
    }

    String description;
    boolean isDone;
}
