package flamingo;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Done tasks are crossed
    }

    public String getStatusAsNumber() {
        return (isDone ? "1" : "0"); // Done tasks are "1"
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Represents task when it is saved to data.txt.
     *
     * @return String with task status and description.
     */
    public String saveTask() {
        return " " + this.getStatusAsNumber() + " " + this.description;
    }

    /**
     * Represents task when it is printed in list.
     *
     * @return String with task status and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
