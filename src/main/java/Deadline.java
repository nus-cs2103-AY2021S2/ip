/*
 * Deadline class to handle tasks that are need to be done before a specific date/time
 */
public class Deadline extends Task {
    protected final String by;

    /**
     * Constructor for DeadLine class
     * @param description details of the task
     * @param by specific date/time to complete the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Displays simplified version of task type, description and deadline of tasks in Duke.txt
     * @return String format regarding the task deadline information
     */
    @Override
    public String formatTask() {
        return String.format("D | %s", super.formatTask());
    }

    /**
     * Displays task type, description and deadline of task
     * @return String format regarding the task deadline information
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
