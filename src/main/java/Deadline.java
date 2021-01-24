/**
 * A Deadline task that inherits Task.
 */
public class Deadline extends Task{

    /** Date/Timing information for task to be completed by. */
    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * A toString unique for Deadline Task.
     * @return Label for Deadline - "D", the description of the task, followed by the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
