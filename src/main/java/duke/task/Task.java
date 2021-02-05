package duke.task;

/**
 * Represents any task specified with task description and status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected int status;

    /**
     * Constructor for Event class.
     * @param description Description of the task.
     * @param status To check if the task is done.
     */
    public Task(String description, int status) {
        this.description = description;
        this.status = status;
        if (status == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Gets the task description.
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the icon to display on the user interface based on the task status.
     * @return Tick for Done task and Cross for Not Done Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Updates the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        this.status = 1;
    }

    /**
     * Converts task details into txt format for local storage.
     * @return The text stored in the local file.
     */
    public String toTxt() {
        return String.format("| %s | %s", isDone ? "1" : "0", this.description);
    }

    /**
     * Generates event details displayed on the user interface.
     * @return Message output for Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
