package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.common.Utils;

/**
 * Represents a Event {@code Task}.
 */
public class Event extends Task {

    private final LocalDateTime eventDateTime;

    /**
     * Constructor for Event {@code Task}, specifying the description and occurrence date.
     *
     * @param description description of the event
     * @param eventDateTime occurrence date of the event
     */
    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = LocalDateTime.parse(eventDateTime);
    }

    /**
     * Constructor for Event {@code Task}, specifying the task's status, description and occurrence date
     *
     * @param done integer value to indicate the event's status
     * @param description description of the event
     * @param eventDateTime occurrence date of the event
     */
    public Event(int done, String description, String eventDateTime) {
        super(done, description);
        this.eventDateTime = LocalDateTime.parse(eventDateTime);
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public LocalDate getEventDate() {
        return eventDateTime.toLocalDate();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Utils.formatDate(eventDateTime));
    }

    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, eventDateTime);
    }
}
