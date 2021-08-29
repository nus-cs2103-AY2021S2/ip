import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are Tasks that must be completed before a certain date.
 */
public class Deadline extends Task implements EventDeadline {

    protected LocalDate by;

    /**
     * Creates and initializes a Deadline object.
     *
     * @param description The description of the Deadline.
     * @param by The date which the Deadline must meet.
     */
    Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        super.taskType = DEADLINE_NUMBER;
    }

    /**
     * Returns the date of the Event object.
     *
     * @return The LocalDate of the Deadline object.
     */
    public LocalDate getDateInfo() {
        return this.by;
    }

    /**
     * Creates A string representation of the Deadline object.
     *
     * @return A string representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}
