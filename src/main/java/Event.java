import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Event.
 * An Event is represented by a name and time of the event,
 * both in the form of a String.
 */
public class Event extends Task {

    static final String DATE_TIME_FORMAT = "MMM dd yyyy h:mm a";
    protected LocalDateTime time;

    /**
     * Creates a new Event with the description and time of event.
     *
     * @param name Description of Event.
     * @param time Time of event.
     */
    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public String parseEventDateTime(LocalDateTime details) {
        return details.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + parseEventDateTime(time) + ") " + (isThereTag ? getTag() : "");
    }
}
