package duke.task;

/**
 * A Task class which represents a task.
 */
public class Task {
    protected char type;
    protected String description;
    protected boolean isDone;

    /**
     * Constructs Task.
     *
     * @param type        The type of task.
     * @param isDone      Determines if task is completed.
     * @param description Task description.
     */
    public Task(char type, int isDone, String description) {
        this.type = type;
        this.description = description;
        this.isDone = isDone == 1;
    }

    /**
     * Creates String to be stored in the data file.
     *
     * @return String in the format to be stored in data file.
     */
    public String getFileString() {
        return String.format("%c // %d // %s", this.type, isDone ? 1 : 0, this.description);
    }

    /**
     * Creates status icon.
     *
     * @return Status icon of task whether completed or not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
