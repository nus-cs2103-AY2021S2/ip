import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events are Tasks that take place at a certain and date.
 */
public class Event extends Task implements EventDeadline {

    protected LocalDate time;

    Event(String description, LocalDate time) {
        super(description);
        this.time = time;
        super.taskType = EVENT_NUMBER;
    }



    /**
     * Creates a string representation of the Event object.
     *
     * @return a string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }

    /**
     * Retuens the date of the Event object.
     *
     * @return The LocalDate of the Event object.
     */
    @Override
    public LocalDate getDateInfo() {
        return this.time;
    }
}
