package duke.task;

/**
 * Todo tasks are tasks that do not have to be completed by a specified date or during a specified time period.
 *
 * @author  Nicole Ang
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task specifying its description.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
