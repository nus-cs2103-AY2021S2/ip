package Duke.Task;

/**
 * A deadline is a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * The Deadline class constructor has 2 parameters:
     * the description about a specific deadline and the time that the deadline needs to be done.
     * @param description The description about the deadline.
     * @param by The time that the deadline needs to be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    /**
     * Gets the time that the deadline needs to be done.
     * @return The time that the deadline needs to be done.
     */
    @Override
    public String getTime() {
        return " | " + by;
    }

    /**
     * Gets the string representation for this Deadline object.
     * @return The string representation for this object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.trim() + ")";
    }
}
