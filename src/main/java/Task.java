public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a task.
     *
     * @param description task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Put a X for the task that is done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
