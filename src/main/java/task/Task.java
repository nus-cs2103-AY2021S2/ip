package task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task() {
    }

    public String getType() {
        return this.type;
    }


    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public void complete() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "complete" : "incomplete");
    }

    public String getDetails() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
