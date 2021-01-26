import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Events are Tasks that take place at a certain and date.
 */
public class Event extends Task {
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Creates a string representation of the Event object.
     * @return a string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
    }
}
