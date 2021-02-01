package duke.task;

import java.time.LocalDate;

import duke.DukeHelper;

/**
 * Represents a Event {@code Task}.
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Constructor for Event {@code Task}, specifying the description and occurrence date.
     *
     * @param description description of the event
     * @param at occurrence date of the event
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
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
        this.at = LocalDate.parse(at);
    }

    public LocalDate getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), DukeHelper.formatDate(at));
    }

    @Override
    public String toStorageString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }
}
