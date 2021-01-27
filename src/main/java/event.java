import java.time.LocalDate;

/**
 * Type of Task that the user can input.
 */
public class Event extends Task {
    /** Date and duration of the event */
    protected LocalDate date;

    /**
     * Constructor for Event objects.
     * @param description What the event is.
     * @param date The date by which it has to be completed.
     */
    Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Getter which returns the date the Event occurs on.
     * @return The date and duration of the Event.
     */
    public LocalDate getDate() {
        return date;
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
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}
