package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }

    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    //Return tick or X symbols according to task completion status.
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    //Change the completion status of a task to done.
    public void markAsDone() {
        this.isDone = true;
    }

    public String getSaveString() {
        return this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
