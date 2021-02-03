package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class for event tasks
 */
public class Event extends Task {

    protected String at;
    protected LocalDate localAt;

    /**
     * Constructor for event class.
     * Initializes a event with specified description.
     *
     * @param description description of the event task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.localAt = LocalDate.parse(at);
        this.type = 'E';
    }

    public String getAt() {
        return this.at;
    }
    public String getFormattedAt() {
        return localAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getFormattedAt() + ")";
    }
}
