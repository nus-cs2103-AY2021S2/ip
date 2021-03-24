import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Type of Task that the user can input.
 */
public class Deadline extends Task {
    /**
     * Constructor for Deadline objects
     * @param description What the deadline is.
     * @param date The date by which it has to be completed.
     */
    Deadline(String description, LocalDate date, LocalTime time) {
        super(description, date, time);
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
        String formattedDate = dateFormat.format(date);
        String formattedTime = timeFormat.format(time);
        return super.toString() + " (By: " + formattedDate + " by " + formattedTime + ")";
    }
}
