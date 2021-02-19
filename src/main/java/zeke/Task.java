package zeke;

/**
 * Task class to represent a task, which is a todo, deadline or event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Constructor for Task class.
     * Initializes a Task object with given description and marks it as not done.
     *
     * @param description task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon depending on whether this task is done.
     * @return X if done or blank if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or blank space, X means done
    }

    /**
     * Marks this task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Returns the type of this task.
     *
     * @return Type of this task as char.
     */
    public char getType() {
        return this.type;
    }

    /**
     * Returns the description of this task.
     *
     * @return A String description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a boolean depending on whether this task is done.
     *
     * @return Boolean depending on whether this task is done.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
