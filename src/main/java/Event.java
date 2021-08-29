import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events are Tasks that take place at a certain and date.
 */
public class Event extends Task implements EventDeadline {

    protected LocalDate date;

    /**
     * Creates and initializes an Event object.
     *
     * @param description The description of the Event.
     * @param date The date which the Event takes place.
     */
    Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        super.taskType = EVENT_NUMBER;
    }



    /**
     * Creates a string representation of the Event object.
     *
     * @return a string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }

    /**
     * Returns the date of the Event object.
     *
     * @return The LocalDate of the Event object.
     */
    @Override
    public LocalDate getDateInfo() {
        return this.date;
    }
}
