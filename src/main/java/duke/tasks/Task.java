package duke.tasks;

/**
 * Task has a description and maintains a isDone state.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private final String TICK = "\u2713";
    private final String CROSS = "\u2718";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a tick or cross string depending on whether the task is marked as isDone or not.
     *
     * @return A tick or cross string.
     */
    public String getStatusIcon() {
        return (isDone ? TICK : CROSS); //return tick or X symbols
    }

    /**
     * Toggles the value of isDone.
     *
     * @return Updated boolean value after toggling.
     */
    public boolean toggleIsDone() {
        isDone = !isDone;
        return isDone;
    }

    /**
     * Updates isDone to true and returns true.
     *
     * @return The boolean value, true.
     */
    public boolean setIsDone() {
        isDone = true;
        return isDone;
    }

    /**
     * Generates the string to be saved for the next user session locally.
     *
     * @return String representing task state.
     */

    public String getDescription() {
        return description;
    }

    public String saveString() {
        return isDone ? "Task *** 1 *** " + description : "Task *** 0 *** " + description;
    }

    /**
     * Returns string representation of Task.
     *
     * @return String representation of task.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
