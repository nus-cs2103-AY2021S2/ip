/**
 * Represents tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getType() {
        return "TASK";
    }

    /**
     * Returns String format of task
     *
     * @return String format of task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Changes boolean isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void editTask(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Formats data for saving into text file.
     *
     * @return isDone | description
     */
    public String formatData() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
