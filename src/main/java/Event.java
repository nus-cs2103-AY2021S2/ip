package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates information and state of a Event.
 * For tasks that lasts for a certain period at certain location.
 */
public class Event extends Task {
    /** Location of event. */
    protected LocalDate at;

    /**
     * Initialises a new Event with text description and date.
     */
    public Event(String description, String at) {
        super(description);
        if (at.contains("-")) {
            this.at = LocalDate.parse(at);
        } else {
            this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), 
                super.toString(), this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
