package duke.tasks;

/**
 * Todo task
 */
public class Todo extends Task {
    /**
     * Todo constructor
     *
     * @param taskName Name of task
     * @param isCompleted Completion status of task
     */
    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    /**
     * Generates string to be stored in local disk
     * @return String with specific format
     */
    public String toStorageString() {
        return "T || " + (this.isCompleted ? "1" : "0") + " || " + this.taskName;
    }

    @Override
    public String getTime() {
        return "Todo has no time.";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
