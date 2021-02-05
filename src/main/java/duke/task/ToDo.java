package duke.task;

/**
 * Class for ToDo tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs an undone ToDo Task with a name.
     *
     * @param taskName Name of the Task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a ToDo Task with a name with status.
     *
     * @param taskName Name of the Task.
     * @param done Status of the Task, done or not done.
     */
    public ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    /**
     * Returns a string representation of the ToDo Task.
     *
     * @return String representation of the ToDo Task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parse the java.duke.controller.task to comply with CSV format.
     *
     * @return A string that complies with CSV format.
     */
    public String parseToCSVRow() {
        return "T," + super.isDone() + "," + super.getTaskName();
    }
}
