import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Type of Task that the user can input.
 */
public class Event extends Task {

    /**
     * Constructor for Event objects.
     * @param description What the event is.
     * @param date The date by which it has to be completed.
     */
    Event(String description, LocalDate date, LocalTime time) {
        super(description, date, time);
    }

    /**
     * Getter which returns the identifier for the type of Task it is.
     * @return E for Event.
     */
    @Override
    public String getInitial() {
        return "E";
    }

    /**
     * String representation of an Event object.
     * @return String with the description and date of the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + dateFormat.format(date) + " at " + timeFormat.format(time) + ")";
    }
}
