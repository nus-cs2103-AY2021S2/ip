package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an "event" task.
 */
public class Event extends Task {
    private LocalDateTime eventTime;

    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventTime.format(DateTimeFormatter.ofPattern(" d MMM yyyy HH:mm"))
                + ")";
    }
}

