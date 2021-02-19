import java.time.LocalDateTime;

/**
 * Represents the Event Task.
 */
public class Event extends TimedTask {

    /**
     * Initializes an Event Task with the specified description and dateTime.
     *
     * @param description Description of the Event Task.
     * @param dateTime Date and Time the Event Task occurs at.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description, dateTime, "E");
    }

    /**
     * Initializes an Event Task with the specified description, timeCreated, and dateTime.
     *
     * @param description Description of the Event Task.
     * @param timeCreated DateTime when this Event object was created.
     * @param dateTime Date and Time the Event Task occurs at.
     */
    public Event(String description, LocalDateTime timeCreated, LocalDateTime dateTime) {
        super(description, timeCreated, dateTime, "E");
    }

    public String toString() {
        return super.toString() + " (at: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }
}
