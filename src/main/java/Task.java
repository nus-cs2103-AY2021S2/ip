/**
 * Represents a Task consisting of a description and completion status.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Creates a task instance.
     *
     * @param description String describing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status symbol of the task depending on the completion status
     *
     * @return String with ths status character
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * Retrieves the task description.
     *
     * @return String with ths task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String which gives information about the task.
     *
     * @return A String containing information about the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String fileString() {
        return this.description;
    }
}