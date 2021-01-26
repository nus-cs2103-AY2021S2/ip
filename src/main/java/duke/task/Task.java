package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "task";
    }

    public String getStatusIcon() {
        //return tick or cross symbols
        return (isDone ? "\u2713" : " ");
    }

    public void done() {
        this.isDone = true;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIsDone() {
        return (this.isDone) ? "T" : "F";
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
