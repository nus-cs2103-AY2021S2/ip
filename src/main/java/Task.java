public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task class.
     * @param description A brief description of the task.
     */

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Display whether a task is completed.
     * @return A cross symbol if task is incomplete, a tick symbol if task is completed.
     */

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Update the task as completed.
     */

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
