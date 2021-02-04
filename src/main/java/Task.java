public class Task {

    protected String description;
    protected boolean isDone;

    enum Level {
        low,
        average,
        high
    }


    /**
     * Main constructor that accepts a description of the task, and by default
     * assigns a false value to the undone task.
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overriden method to mark a task as done.
     * @param description Description of method
     * @param isDone Boolean flag indicating whether task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : "";
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}
