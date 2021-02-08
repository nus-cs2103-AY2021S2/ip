package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime atDateTime;

    /**
     * Initializes an event task with a description and a datetime.
     *
     * @param description Description of the event task.
     * @param atDateTime  The event's time.
     */
    public Event(String description, LocalDateTime atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    /**
     * Converts the event's time to a <code>String</code>.
     *
     * @return A formatted date string corresponding to the event's time.
     */
    public String getAtDateTimeString() {
        return this.atDateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    /**
     * Retrieves the event task's description and status.
     *
     * @return A formatted string displaying the event task's description and status.
     */
    public String getStatusString() {
        return "[E]" + super.getStatusString() + " (at: " + this.getAtDateTimeString() + ")";
    }
}
