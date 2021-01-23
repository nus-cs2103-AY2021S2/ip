public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, "0");
        this.by = by;
    }

    /**
     * Constructor of the Deadline class
     * @param description A brief description of the deadline.
     * @param by The time/date that the task must be completed by.
     */

    public Deadline(String description, String isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getDetails() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
