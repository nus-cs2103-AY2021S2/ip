import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are Tasks that must be completed before a certain date.
 */
public class Deadline extends Task implements EventDeadline {

    protected LocalDate by;

    Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        super.taskType = DEADLINE_NUMBER;
    }

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
