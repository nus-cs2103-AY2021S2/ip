package task;

public class Task {
    protected StringBuilder description = new StringBuilder();
    protected boolean isDone;

    public Task(String[] description) {
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public void complete() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
