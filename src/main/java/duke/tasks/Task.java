package duke.tasks;

/**
 * Responsible for containing different types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Constructs a Task with the given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = ' ';
    }

    /**
     * Constructs a Task with the given description and type of Task.
     *
     * @param description Description of task.
     * @param type Type of task.
     */
    public Task(String description, char type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns a string indicating the completion status of task.
     *
     * @return string indicating completion status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes the completion status of task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /** Returns the format of string of task to be saved.
     *
     * @return String of task to be saved.
     */
    public String getSaveString() {
        return this.type + " | " + getIsDoneString() + " | " + this.description;
    }

    private String getIsDoneString() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Returns the string of the task.
     *
     * @return string of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
