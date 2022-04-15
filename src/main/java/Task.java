/**
 * Represents a task that is created from the user's command line input.
 *
 * @author Damith C. Rajapakse, Jeffry Lum
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task object.
     *
     * @param description the task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates if a task is completed or still in progress.
     *
     * @return the task status.
     */
    public String getStatus() {
        return (isDone) ? "COMPLETED!" : "INPROGRESS";
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

}
