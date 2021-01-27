package duke.tasks;

/**
 * Task class models an actual task that the user is about to do or has completed
 * Its details include the description as well as the type of the task
 */
public class Task {
    /** description about task */
    protected String description;

    /** status on whether task is completed */
    protected boolean isDone;

    /** type of Task */
    protected String type = "";
    
    /**
     * @param description the description of the task
     * @param  type the task type
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns a String icon based on whether task has been completed
     *
     * @return Status Icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    /**
     * Returns the status icon with enclosing brackets
     *
     * @return Status Icon with brackets
     */
    public String getStatus() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Sets a task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the character type
     *
     * @return Character
     */
    public char getType() {
        return type.charAt(1);
    }

    /**
     * Returns the task description
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * toString method of Task which prints out details of the task
     */
    @Override
    public String toString() {
        return "       " + this.getStatus() + " " + this.description;
    }
}