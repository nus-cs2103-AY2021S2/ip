import java.util.List;
import java.time.LocalDate;

/**
 * Represents a Task.
 * Includes description of the task and an indicator of whether it is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
    }

    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    /**
     * Set task as completed.
     */
    public void completed() {
        isDone = true;
    }

    private String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }

    /**
     * Export data into a standardised format.
     */
    protected abstract List<String> exportData();
}
