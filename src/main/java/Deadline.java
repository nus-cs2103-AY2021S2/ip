import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates an deadline instance.
     *
     * @param description String describing the deadline
     * @param by          String containing the deadline time
     */
    public Deadline(String description, LocalDate by) {
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
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a String which gives information about the deadline in the
     * format to be stored in a file.
     *
     * @return A String containing information about the deadline.
     */
    @Override
    public String fileString() {
        int statusNum = this.isDone ? 1 : 0;
        return "D | " + statusNum + " | " + this.description + " | " + this.by;
    }
}