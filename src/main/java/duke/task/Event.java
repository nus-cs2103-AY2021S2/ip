package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Initialises the task with a description and
     * a duration represented by a date.
     *
     * @param description Description of the task.
     * @param at Task duration.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Retrieves the duration of the task.
     *
     * @return Task duration.
     */
    public LocalDateTime getAt() {
        return this.at;
    }

    /**
     * Formats the task string representation.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
