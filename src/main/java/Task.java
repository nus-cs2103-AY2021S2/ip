
/**
 * A task object that describes what the task is.
 */
public class Task {

    protected static int TODO_NUMBER = 0;
    protected static int DEADLINE_NUMBER = 1;
    protected static int EVENT_NUMBER = 2;


    protected String description;

    protected boolean isDone;

    protected int taskType;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates whether a task is completed. If that is the case, a tick will be displayed.
     * @return a tick if isDone is true. Else, it will return an empty space.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : " ");
    }

    /**
     * Changes the status of a Task from undone to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the type of task.
     * @return an integer representing the type of task.
     */
    public int getTaskType() {
        return this.taskType;
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
}
