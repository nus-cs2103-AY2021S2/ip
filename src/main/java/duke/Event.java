package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task that has to
 * be attended at a certain date and time.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Class constructor.
     * @param description the details of the event.
     * @param at the date and time to attend the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the String representation of this Event.
     * @return the String representation of this Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma"))
                + ")";
    }
}
