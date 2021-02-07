package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Initializes an event with its description and time.
     *
     * @param description Description of the event.
     * @param at          Time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts the event to a string which will be saved in a file.
     *
     * @return String representing the event in its save format.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0")
                + " | " + description + " | " + Parser.parseDateTimeToString(at);
    }

    /**
     * Converts the event to a string.
     *
     * @return String representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.parseDateTimeToString(at) + ")";
    }
}
