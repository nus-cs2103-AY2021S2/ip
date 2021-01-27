import java.time.LocalDate;

/**
 * Type of Task that the user can input.
 */
public class Deadline extends Task {
    /** Date and deadline of the Deadline */
    protected LocalDate date;

    /**
     * Constructor for Deadline objects
     * @param description What the deadline is.
     * @param date The date by which it has to be completed.
     */
    Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Getter which returns the date the deadline should be completed be by.
     * @return the deadline by which the Task should be completed.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter which returns the identifier for the type of Task it is.
     * @return D for Deadline.
     */
    @Override
    public String getInitial() {
        return "D";
    }

    /**
     * String representation of a Deadline object.
     * @return String with the description and date of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By: " + date + ")";
    }
}
