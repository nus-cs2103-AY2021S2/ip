package quackers.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected LocalDateTime at;

    /**
     * Constructs a Event object.
     * @param description Task description.
     * @param at Task date and time.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats the task string representation.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at.format(Event.DATETIME_FORMATTER));
    }
}
