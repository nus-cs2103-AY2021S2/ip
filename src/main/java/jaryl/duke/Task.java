package jaryl.duke;

/**
 * Task with a description and type that can be added to a task list
 * Can exist as Todo, Deadline or Event
 * @author LOH FAH YAO, JARYL
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor to instantiate a new Task
     * @param description   description of task
     * @param type          task type in "T", "D" or "E"
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Retrieves the description of the task
     * @return  Task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the status icon of the task depending whether it is done
     * @return  Task status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Toggles the status of the task
     */
    public void toggleStatus() {
        this.isDone = !this.isDone;
    }

    /**
     * Retrieves the task type
     * @return  the task type in "T", "D" or "E"
     */
    public String getType() {
        return this.type;
    }

    /**
     * Reformats string format of Task when writing to disk
     * @return Task in reformatted String format
     */
    public String fileFormat() {
        return this.type + " | " + (isDone ? "1 | " : "0 | ") + this.description;
    }

    /**
     * Converts Task to string format
     * @return Task in original String format
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}