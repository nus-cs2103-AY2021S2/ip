import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task that inherits Task.
 */
public class Deadline extends Task {

    /**
     * Date/Timing information for task to be completed by.
     */
    protected String by;

    public Deadline(String description, String by, boolean isDone, boolean existing) {
        super(description, isDone);
        if (existing) {
            this.by = by;
        } else {
            LocalDate date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }

    /**
     * A toString unique for Deadline Task.
     *
     * @return Label for Deadline - "D", the description of the task, followed by the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
