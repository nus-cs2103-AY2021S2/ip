/**
 * Represents a Task.
 * Includes description of the task and an indicator of whether it is completed.
 */
public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        done = false;
    }

    /**
     * Set task as completed.
     */
    public void completed() {
        done = true;
    }

    @Override
    public String toString() {
        String status = done ? "X" : " ";
        return String.format("[%s] %s", status, description);
    }
}
