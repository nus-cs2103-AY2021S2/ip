import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event task that inherits Task.
 */
public class Event extends Task {

    /**
     * Date/Timing information for task to be carried out at.
     */
    protected String eventDate;

    public Event(String description, String eventDate, boolean isDone, boolean taskExists) {
        super(description, isDone);
        if (taskExists) {
            this.eventDate = eventDate;
        } else {
            LocalDate date = LocalDate.parse(eventDate);
            this.eventDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }

    /**
     * A toString uniquely for Event Task.
     *
     * @return Label for Event - "E", the description of the task, followed by the timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
