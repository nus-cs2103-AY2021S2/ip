import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class that extends the Task class. A Deadline object corresponds to a Task object which has a description
 * deadline and time.
 */
public class Deadline extends Task {
    LocalDate by;
    LocalTime time;

    public Deadline(String description, LocalDate by, LocalTime time) {
        super(description);
        this.by = by;
        this.time = time;
        new Object().toString();
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + " " + time + ")";
    }
}