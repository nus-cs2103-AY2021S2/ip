public class Deadline extends Task {
    protected String by;

    /**
     * Constructor of the Deadline class
     * @param description A brief description of the deadline.
     * @param by The time/date that the task must be completed by.
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
