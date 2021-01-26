package duke.task;

/**
 * ToDo class to handle tasks without any deadline (no date /time)
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class
     *
     * @param description Details of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Displays simplified version of task type and description in Duke.txt
     *
     * @return String format regarding the ToDo task
     */
    @Override
    public String formatTask() {
        return String.format("T | %s", super.formatTask());
    }

    /**
     * Displays task type and task description
     *
     * @return String format of ToDO task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
