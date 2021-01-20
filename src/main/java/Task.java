public class Task {
    protected String description;
    protected boolean isDone;
    protected int index = 0;


    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public String getStatusIcon() {
        return (isDone ? "[ ]" : "[X]"); //return tick or X symbols
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public String printTask() {
        return this.getStatusIcon() + " " + description;
    }

}
