package duke.task;

/**
 * Duke.Tasks.Task created by user
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Duke.Tasks.Task
     *
     * @param description Duke.Tasks.Task Name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets a task status
     *
     * @return Tick if marked as done, empty string if not done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick
    }

    /**
     * Returns task name
     *
     * @return Duke.Tasks.Task name
     */
    public String getDescription() {
        return String.format("%s", description);
    }

    public String writeContentFormat() {
        return String.format("%d | %s", isDone ? 1 : 0, getDescription());
    }

    /**
     * Returns status icon and task name
     *
     * @return Status icon and task name
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

}
