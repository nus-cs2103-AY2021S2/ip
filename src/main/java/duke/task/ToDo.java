package duke.task;

public class ToDo extends Task {

    /**
     * Represents a task that has no deadline.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * Returns formatted string of the todo task details to store in harddisk file.
     * @return Formatted string.
     */
    @Override
    public String getFormattedData() {
        return "T | " + super.getFormattedData();
    }

    /**
     * Returns description of the todo task and status of the task.
     * @return String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
