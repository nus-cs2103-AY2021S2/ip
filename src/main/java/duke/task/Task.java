package duke.task;

/**
 * <code>Task</code> class represents any task with a description - deadline, event, todo.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for <code>Task</code> class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    /**
     * Generates the appropriate symbol according to whether the task is marked as done.
     *
     * @return String format of the symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    /**
     * Indicates the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Generates the date of the task.
     *
     * @return Date of the task in String format.
     */
    public String getTaskDate() {
        return "";
    }

    /**
     * Generates the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Generates the specific type of the task - deadline, event, todo.
     *
     * @return Specific type of task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done otherwise false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Generates details of a task - description, done.
     *
     * @return String output for a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
