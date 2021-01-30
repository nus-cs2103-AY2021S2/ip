import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    protected LocalDateTime at;

    /**
     * Initializes an Event Task with the specified description and dateTime.
     * @param description Description of the Event Task.
     * @param dateTime Date and Time the Event Task occurs at.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description, "E");
        at = dateTime;
    }

    public String toString() {
        return super.toString() + " (at: " + at.format(DATE_TIME_FORMAT) + ")";
    }

    public String toLog() {
        return super.toLog() + " | " + at;
    }
}
