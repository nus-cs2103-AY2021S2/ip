import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline type of Task.
 * Inherits from the superclass Task.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor to create a Deadline Task.
     * Keeps track of the date the Deadline is by.
     * @param name Name of the Deadline.
     * @param by Date the Deadline is due by.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructor used when retrieving data from hard drive.
     * @param name Name of the Deadline.
     * @param by Date that the Deadline is due by.
     * @param done If the Deadline is done or not.
     */
    public Deadline(String name, LocalDate by, boolean done) {
        super(name, done);
        this.by = by;
    }

    /**
     * Getter for the date the Deadline is due by.
     * @return The date that the Deadline is due by.
     */
    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
