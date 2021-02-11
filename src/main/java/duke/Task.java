package duke;

/**
 * The task class represents a task. To be inherited by specific types of tasks.
 */
public class Task {
    private String task;
    private boolean done;

    /**
     * Construct a task, which is not done by default
     * @param task the task
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Mark a task as done
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Get the task description
     * @return the description string of the task
     */
    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.getTask();
    }
}
