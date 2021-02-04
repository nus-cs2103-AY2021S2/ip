import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates task objects categorised as a "deadline" task
 *
 * @author Amanda Ang
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Construct Deadline object
     *
     * @param description the description of the Deadline task
     * @param by the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Reschedule Deadline object
     *
     * @param updated the updated deadline of the Deadline task
     */
    public void updateDate(LocalDate updated) {
        this.by = updated;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | "
               + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}