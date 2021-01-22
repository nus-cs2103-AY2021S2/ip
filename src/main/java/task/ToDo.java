package task;

import java.time.LocalDate;

/**
 * Class for ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs an undone ToDo task with a name.
     *
     * @param taskName Name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a ToDo task with a name with status.
     *
     * @param taskName Name of the task.
     * @param done Status of the task, done or not done.
     */
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parse the task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public String parseToCSVRow() {
        return "T," + super.isDone() + "," + super.getTaskName();
    }
}
