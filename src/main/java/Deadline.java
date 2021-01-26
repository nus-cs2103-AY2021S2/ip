import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates information and state of a Deadline.
 * For tasks that must be ccompletely by a certain time.
 */
public class Deadline extends Task {
    /** Deadline of task. */
    protected LocalDate by;

    /**
     * Initialises a new Deadline with text description.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), 
            super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
