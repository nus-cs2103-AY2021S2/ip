package duke;

/**
 * Task creates an abstraction of everyday tasks that user perform.
 */
public class Task {
    public static final String SHORTHAND = "INVALID";
    protected final String taskDescription;
    protected boolean isDone;

    /**
     * Task constructor instantiates a task class
     * @param taskDescription
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Decides the status icon of the task, X for completed and "" for not completed.
     * @return The completion status of the task
     */
    private String getStatusIcon() {
        return this.isDone ? "X" : "";
    }

    /**
     * Marks a task as done/completed.
     */
    public void markAsDone() {
        // TODO consider adding assertion here
        isDone = true;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean bool) {
        isDone = bool;
    }

    public String getTaskDescription () {
        return taskDescription;
    }

    /**
     * Shows the details about the task .
     * @return A formatted string with details of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskDescription;
    }

}
