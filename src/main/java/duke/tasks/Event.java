package duke.tasks;

import java.time.LocalDateTime;

import duke.common.Utils;

/**
 * Represents a Event {@code Task}.
 */
public class Event extends Task {

    private final LocalDateTime at;

    /**
     * Constructor for Event {@code Task}, specifying the description and occurrence date.
     *
     * @param description description of the event
     * @param at occurrence date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at);
    }

    /**
     * Constructor for Event {@code Task}, specifying the task's status, description and occurrence date
     *
     * @param done integer value to indicate the event's status
     * @param description description of the event
     * @param at occurrence date of the event
     */
    public Event(int done, String description, String at) {
        super(done, description);
        this.at = LocalDateTime.parse(at);
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Utils.formatDate(at));
    }

    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }
}
