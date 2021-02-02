package duke.tasks;

/**
 * Represents the Task that contains the description of the task with a boolean to show if the
 * task is done.
 */
public abstract class Task {
    protected boolean isDone;
    protected final String description;

    /**
     * Constructs a Task that contains the description and defaulted as not done.
     * @param description description of the Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Sets the task as done.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Returns a data representation of the task to be saved in the save.txt file.
     * @return data representation of Task.
     */
    public String data() {
        String done = isDone ? "1" : "0";
        return String.format("%s | %s", done, description);
    }

    /**
     * Returns string representation of the Task to be shown to the user.
     * @return string representation of the Task.
     */
    @Override
    public String toString() {
        String output;
        if (isDone) {
            output = String.format("[X] %s", description);
        } else {
            output = String.format("[ ] %s", description);
        }
        return output;
    }
}
