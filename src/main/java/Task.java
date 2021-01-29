/**
 * Represents a task that user has not yet done, or has completed.
 */
public class Task {
    public String task;
    public boolean isDone;

    public Task(String task) {
        task = task.strip();
        this.task = task;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        task = task.strip();
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Returns a tick if task is done, and cross otherwise.
     *
     * @return String a tick or cross.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + task;
    }

    /**
     * Sets the isDone variable to true.
     */
    public void doneTask() {
        isDone = true;
    }
}
