/**
 * Class Task contains different task that Duke chatbot is doing.
 *
 * @version 21 Jan 2021.
 * @author Zhang Peng.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected int index = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to get the status icon of the task.
     * @return String This returns the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    /**
     * This method is used to mark the task as done
     * @return Task This returns the Task object with updated status icon.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * This is the toString() method of the class
     * @return String This returns the string representation of the task.
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * This method is used to decrase the number attached to that task.
     * @return Task This returns the Task object with updated index number.
     */
    public Task decreaseIndex() {
        this.index--;
        return this;
    }
}
