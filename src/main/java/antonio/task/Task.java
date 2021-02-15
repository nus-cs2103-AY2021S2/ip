package antonio.task;

/**
 * Represents a task.
 */
public class Task {

    protected int id;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param description Name of the command.
     * @param id ID of task
     */
    public Task(String description, int id) {
        this.description = description;
        this.id = id;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Gets the ID of the task.
     * @return The ID of the task.
     */
    public int getID() {
        return id;
    }

    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is complete.
     * @return A boolean value representing the completion status of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * To string method for a check box based on task completion status.
     * @return A string representing a check box
     */
    protected String checkBoxToString() {
        if (isDone) {
            return "[X] ";
        } else {
            return "[] ";
        }
    }

}
