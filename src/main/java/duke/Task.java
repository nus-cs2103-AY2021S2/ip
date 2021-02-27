package duke;

/**
 * Abstract class that represents a task.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a new Task object with a description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * The status icon is "X" if the task is completed and " " otherwise.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as completed.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the user command required to create this Task object.
     *
     * @return User command.
     */
    public abstract String getTaskCommand();

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns true if the task is completed and false otherwise.
     *
     * @return True if the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if the task description contains the keyword.
     *
     * @param keyword Keyword
     * @return True if the task description contains the keyword.
     */
    public boolean descriptionContains(String keyword) {
        return description.contains(keyword);
    }
}
