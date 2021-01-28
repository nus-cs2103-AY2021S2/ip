package duke.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task, representing an event with date and time.
 */
public class Event extends Task {
    private LocalDateTime datetime;

    public Event(String taskName, LocalDateTime datetime) {
        super(taskName);
        this.datetime = datetime;
    }

    /**
     * Returns the date and time of the event.
     * @return date and time of event
     */
    public LocalDateTime getDate() {
        return datetime;
    }

    /**
     * String representation of event
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
