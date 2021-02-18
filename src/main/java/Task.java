/**
 * A class which has description of the task and isDone tells whether a particular task has been completed.
 */
public class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of a Deadline object. If the Task is done return "X", otherwise return " ".
     *
     * @return Status icon of a Task object.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    /**
     * Mark a Task object as done when it is completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}