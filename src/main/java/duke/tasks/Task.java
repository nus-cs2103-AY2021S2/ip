package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

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
     * @return a tick or cross string
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Toggles the value of isDone.
     * @return updated boolean value after toggling.
     */
    public boolean toggleIsDone() {
        isDone = !isDone;
        return isDone;
    }

    /**
     * Updates isDone to true.
     * @return true
     */
    public boolean setIsDone() {
        isDone = true;
        return isDone;
    }

    /**
     * Generates the string to be saved for the next user session locally.
     * @return string representing task state.
     */
    public String saveString() {
        return isDone ? "Task *** 1 *** " + description : "Task *** 0 *** " + description;
    }

    /**
     * Returns string representation of Task.
     * @return string representation of task.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
