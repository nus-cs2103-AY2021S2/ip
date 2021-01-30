package duke.task;

/**
 * Abstract class which is inherited by all Task classes.
 */
public abstract class Task {

    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return tick if Task is done, otherwise empty
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
     * @return formatted String which is saved in the data file
     */
    public abstract String getFormattedStorageString();

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
