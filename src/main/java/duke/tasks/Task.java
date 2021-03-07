package duke.tasks;

/**
 * class Task
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent a task in the task list
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor: creates a new Task
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * markAsDone: marks a task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * getStatusIcon: retrieves the icon that represents the completion status of a task
     * @return status icon
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * saveFormat: creates a formatted representation of a task to be saved
     * @return representation of a task to be saved
     */
    public String saveFormat() {
        return (isDone ? "1" : "0") +  " | " + description;
    }

    /**
     * toString: creates a String representation of a task
     * @return String representation of a task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
