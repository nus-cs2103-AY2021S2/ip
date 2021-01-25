package duke.task;

/**
 * A task for Duke.Tasks.ToDo
 */
public class ToDo extends Task {

    /**
     * Constructor for Duke.Tasks.ToDo
     *
     * @param description Duke.Tasks.Task name
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String writeContentFormat() {
        return String.format("T | %s", super.writeContentFormat());
    }

    /**
     * Returns task type, status icon and task name
     *
     * @return String format specific to Duke.Tasks.ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
