package duke.task;

/**
 * A Task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor which initialises the description of a task and sets it as not done
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "task";
    }

    /**
     * Gets an icon based on the status of the task
     * @return tick if done, blank if not
     */
    public String getStatusIcon() {
        //return tick symbol if done
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Sets the task as done
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Gets the type of the task, todo, deadline or event.
     * @return type of the task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the done status of the task
     * @return T if done, F if not
     */
    public String getIsDone() {
        return (this.isDone) ? "T" : "F";
    }

    /**
     * Overridden toString() method that includes the status icon and task description
     * @return a string containing details of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
