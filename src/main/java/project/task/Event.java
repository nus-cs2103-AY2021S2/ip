package project.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the application.
 * This extends the {@code Task} class.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates an instance of {@code Event}.
     *
     * @param description The event description.
     * @param start The date and time the event begins.
     * @param end The date and time the event ends.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns {@code String} representation of this {@code Event}.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + start.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")) + " to "
                + end.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")) + ")";
    }
}
