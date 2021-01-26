/**
 * A task object that describes what the task is.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected int taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates whether a task is completed. If that is the case, a tick will be displayed.
     * @return a tick if isDone is true. Else, it will return an empty space.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or X symbols
    }

    /**
     * Changes the status of a Task from undone to done.
     * @return a string representation of the Task object with an updated status.
     */
    public String markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        return toString();
    }

    /**
     * Creates a string representation of the Task object.
     * @return a string representation of the Task object.
     */
    @Override
    public String toString() {
        String indicator = getStatusIcon();
        return "[" + indicator + "]  " + description;
    }

    public int getTaskType() {
        return this.taskType;
    }
}
