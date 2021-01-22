public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

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

    //Return type of task
    public char getType() {
        return this.type;
    }

    //Return completion status
    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
