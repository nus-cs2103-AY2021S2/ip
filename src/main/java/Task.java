public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public void complete() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}