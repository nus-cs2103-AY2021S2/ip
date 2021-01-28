package Duke.Task;

/**
 * A class that contains all the information about 1 specific task.
 */

public class Task {
    protected String description;
    protected String type;
    protected boolean isDone;
    /**
     * The Task class constructor has 1 parameter: the description about a specific task.
     * @param description The description about the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }
    /**
     * Gets the status of the task, whether it has been done or not.
     * @return A tick symbol if done, X symbol otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    /**
     * Gets the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the status of the task, whether it has been done or not. This method is used for writing tasks into file.
     * @return 1 if done, 0 otherwise.
     */
    public int getStatusNumber() {
        return (isDone) ? 1 : 0;
    }
    /**
     * Gets the type of the task, whether it is a Deadline, Event or Todo.
     * @return The type of the task.
     */
    public String getType() {
        return type;
    }
    /**
     * Gets the time associated with the task.
     * @return The time associated with the task.
     */
    public String getTime() {
        return "";
    }
    /**
     * Changes the status of a task from not done to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Gets the string representation for this Task object.
     * @return The string representation for this object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
