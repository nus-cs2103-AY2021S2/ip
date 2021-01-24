package duke.tasks;

/**
 * Responsible for containing todo tasks.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask with the given description.
     *
     * @param description Description of the task.
     */
    public TodoTask(String description) {
        super(description, 'T');
    }

    /**
     * Returns the string of the task.
     *
     * @return string of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
