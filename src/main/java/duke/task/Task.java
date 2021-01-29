package duke.task;

/**
 * <code>Task</code> class represents any task with a
 * description - deadline, event, todo.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructor for <code>Task</code> class.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    /**
     * Generate the appropriate symbol according to whether
     * the task is marked as done.
     * @return String format of the symbol.
     */
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2718" : " ");
    }

    /**
     * Indicate the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Generate the date of the task.
     * @return Date of the task in String format.
     */
    public String getTaskDate() {
        return "";
    }

    /**
     * Generate the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Generate the specific type of the task - deadline, event, todo.
     * @return Specific type of task.
     */
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Check if the task is marked as done.
     * @return True if the task is done otherwise false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Generate details of a task - description, done.
     * @return String output for a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
