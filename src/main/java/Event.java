import java.time.LocalDateTime;

/**
 * Represents a task of type Event.
 * An Event is represented by a name and time of the event,
 * both in the form of a String.
 */
public class Event extends Task {

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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeHandler.parseLocalDateTimeIntoString(time) + ") "
                + (isThereTag ? getTag() : "");
    }
}
