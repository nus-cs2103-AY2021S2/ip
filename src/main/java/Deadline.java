/**
 * A task for deadline
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for deadline
     * @param description Task name
     * @param by Specific date/time which task is to be done by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String writeContentFormat() {
        return String.format("D | %s | %s\n", super.writeContentFormat(), by);
    }

    /**
     * Returns task type, status icon, task name and due date
     * @return String format specific to deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
