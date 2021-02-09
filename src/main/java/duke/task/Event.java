package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event
 */
public class Event extends Task {

    /**
     * Constructor
     * @param message Event details
     * @param date Date of the event
     */
    public Event(String message, LocalDate date) {
        super("E", message, date);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
