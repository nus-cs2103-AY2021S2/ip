import java.util.List;

/**
 * Represents a Task.
 * Includes description of the task and an indicator of whether it is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
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

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), description);
    }

    public abstract List<String> exportData();
}
