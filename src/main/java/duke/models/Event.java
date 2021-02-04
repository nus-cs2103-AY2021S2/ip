package duke.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task, representing an event with date and time.
 */
public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    /**
     * Returns the date and time of the event.
     * @return date and time of event
     */
    public LocalDateTime getDate() {
        return dateTime;
    }

    /**
     * String representation of event
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
