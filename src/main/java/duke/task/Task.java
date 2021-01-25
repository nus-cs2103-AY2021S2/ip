package duke.task;

/**
 * Abstract class which is inherited by all Task classes.
 */
public abstract class Task {

    String description;
    boolean isDone;

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

    String getDate() {
        return "";
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * Returns String of Tasks formatted to be saved in the data file.
     *
     * @return formatted String which is saved in the data file
     */
    public abstract String getFormattedString();

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
