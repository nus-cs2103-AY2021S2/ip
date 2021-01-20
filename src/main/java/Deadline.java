/**
 * Represents a task with a deadline specified in the by argument.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a task with a deadline specified in the by argument.
     *
     * @param description Describes the task.
     * @param by          Specifies the deadline of this task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
