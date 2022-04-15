package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates information and state of a Event.
 * For tasks that lasts for a certain period at certain location.
 */
public class Event extends Task {
    /** Location of event. */
    protected LocalDate eventDateTime;

    /**
     * Initialises a new Event with text description and date.
     */
    public Event(String description, String eventDateTime) {
        super(description);
        if (eventDateTime.contains("-")) {
            this.eventDateTime = LocalDate.parse(eventDateTime);
        } else {
            this.eventDateTime = LocalDate.parse(eventDateTime,
                DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), super.toString(),
            this.eventDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
