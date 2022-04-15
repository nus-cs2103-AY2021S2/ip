import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a category of tasks called Deadline, that includes the task
 * and the deadline as to which the task has to be completed by
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initializes a Deadline task object.
     *
     * @param description the task description.
     * @param by the deadline as to which the task has to be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
