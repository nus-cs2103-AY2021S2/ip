package duke.task;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task. A <code>task</code> object has two
 * fields, which are the task name and done-status.
 * And it will be the super class for deadline, event and todo.
 */
public class Task implements Comparable<Task> {
    protected String taskName;
    protected boolean isDone;
    protected int priority;
    protected final static DateTimeFormatter DF_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected final static DateTimeFormatter DF_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    /**
     * Default Constructor for Task object
     * that has no name and done-status as undone.
     *
     */
    public Task() {
        taskName = "";
        isDone = false;
    }
    /**
     * Constructor for Task object
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
        priority = 1;
    }
    /**
     * Constructor for Task object
     *
     * @param taskName The name of the task.
     * @param status The done-status of the task.
     */
    public Task(String taskName, boolean status) {
        this.taskName = taskName;
        isDone = status;
        priority = 1;
    }

    /**
     * Constructor for Task object
     *
     * @param taskName The name of the task.
     * @param status The done-status of the task.
     * @param priority The priority of the task.
     */
    public Task(String taskName, boolean status, int priority) {
        this.taskName = taskName;
        isDone = status;
        this.priority = priority;
    }

    /**
     * Getter for task object's status icon.
     *
     * @return returns a tick if the task is done, otherwise a cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Getter for task object's status.
     *
     * @return returns the task's done-status, if done then true, otherwise false.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Getter for task object's name.
     *
     * @return returns the name of the task as a string.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Getter for task object's priority.
     *
     * @return returns the priority of the task as a string.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Getter for task object's priority icon.
     *
     * @return returns the integer value of priority number of stars.
     */
    public String getPriorityIcon() {
        return "\u2729".repeat(priority);
    }




    /**
     * Getter for task object's name in specific format.
     *
     * @return returns the name of the task as a string in a specific format.
     */
    public String toString() {
        return this.taskName;
    }



    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public int compareTo(Task t) {
        if (priority > t.getPriority()) {
            return 1;
        } else if (priority == t.getPriority() && !isDone && t.getStatus()) {
            return 1;
        }
        return -1;
    }
}
