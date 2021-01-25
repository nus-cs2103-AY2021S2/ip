/**
 * Represents a Deadline task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates an deadline instance.
     *
     * @param description String describing the deadline
     * @param by          String containing the deadline time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String which gives information about the deadline.
     *
     * @return A String containing information about the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileString() {
        int statusNum = this.isDone? 1 : 0;
        return "D | " + statusNum + " | " +this.description + " | " + by;
    }
}