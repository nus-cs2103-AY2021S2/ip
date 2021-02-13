package duke.task;

import java.time.LocalDate;

/**
 * Class for general tasks. Not instantiable as it is an abstract class.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Constructs an undone Task with a name.
     *
     * @param taskName Name of the Task.
     */
    Task(String taskName) {
        this(taskName, false);
    }

    /**
     * Constructs a Task with a name and status.
     *
     * @param taskName Name of the Task.
     * @param isDone Status of the Task, done or not done.
     */
    Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the Task.
     *
     * @return The name of the Task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns status of the Task.
     *
     * @return Status of the Task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets status of the Task.
     *
     * @param isDone Status of the Task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string representation of the Task.
     */
    public String toString() {
        String checkBox = this.isDone ? "[X]" : "[ ]";
        return checkBox + " " + this.taskName;
    }

    /**
     * Parse the Task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public abstract String parseToCsvRow();

    /**
     * Returns the time of the Task.
     *
     * @return Time of the Task.
     */
    public LocalDate getTaskTime() {
        return null;
    }
}
