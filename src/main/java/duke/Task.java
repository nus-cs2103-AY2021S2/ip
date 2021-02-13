package duke;

/**
 * A task
 */
public class Task {

    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for a task
     * @param description Details of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Displays the details of the task
     * @return Formatted string which shows the description and status of the task
     */
    @Override
    public String toString() {
        String done = this.isDone ? "\u2713" : "X";
        return "[" + done + "] " + this.description;
    }
}
