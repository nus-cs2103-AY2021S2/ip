package percy.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void doTask() {
        this.isDone = true;
    }

    /*
     public Task doTask() {
        return new Task(this.description, true);
    }
     */

    public String getStatusNumber() {
        return (isDone ? "1" : "0");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public String formatToFile() {
        return this.getStatusNumber() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    //...
}

