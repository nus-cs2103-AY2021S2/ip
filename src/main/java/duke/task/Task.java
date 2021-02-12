package duke.task;

/**
 * Abstract class which is inherited by all Task classes.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Creates a task object and sets isDone to false by default.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns status icon of task.
     *
     * @return tick if Task is done, otherwise empty.
     */
    public String getStatusIcon() {
        return isDone ? "[\u2713] " : "[ ] ";
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Returns String of Tasks formatted to be saved in the data file.
     *
     * @return formatted String which is saved in the data file.
     */
    public abstract String getFormattedStorageString();

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
