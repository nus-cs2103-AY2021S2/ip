import java.time.LocalDateTime;

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

    public String toString() {
        return super.toString() + " (at: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + dateTime;
    }
}
