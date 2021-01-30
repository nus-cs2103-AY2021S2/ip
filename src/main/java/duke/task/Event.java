package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task type event.
 */
public class Event extends Task {

    private final LocalDateTime at;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Builder for Event.
     *
     * @param description Task description.
     * @param at Finish by this time.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getSaveTime() {
        return at.format(formatter);
    }

    @Override
    public String getSaveType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(formatter) + ")\n";
    }
}
