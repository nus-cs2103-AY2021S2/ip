package duke;

/**
 * This class is an object-oriented-representation of a task event
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns statusIcon in the form of 1 or 0.
     * 1 means done, 0 means undone.
     *
     * @return String representation of statusicon.
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns String representation of task description
     *
     * @return String of description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done by changing the boolean value isDone to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Update time of the task
     *
     * @param time new value of time
     */
    public void updateTime(String time) {};

    /**
     * Update description of the task
     *
     * @param desc new description of the task
     */
    public void updateDesc(String desc) {
        this.description = desc;
    };

    /**
     * Returns a segment of text representing the current task, this text is in the format
     * which is to be saved to file
     *
     * @return String of description of task
     */
    public String saveToFile() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }

    /**
     * Returns a segment of text representing the current task, this text is in the format
     * which is to be displayed to the user
     *
     * @return String of description of task
     */
    public String toString() {
        return " | " + getStatusIcon() + " | " + getDescription();
    }
}
