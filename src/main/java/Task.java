public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //Return tick or X symbols according to task completion status.
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    //Change the completion status of a task to done.
    public void markAsDone() {
        this.isDone = true;
    }

    //Change the completion status of a task to not done.
    public void markAsNotDone() {
        this.isDone = false;
    }
}
