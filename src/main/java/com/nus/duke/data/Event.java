package com.nus.duke.data;

import com.nus.duke.parser.DateParser;
import java.time.LocalDateTime;

/**
 * Event is a type of Task that includes a time. Note: Date/time are treated as Strings, so any
 * formats are accepted.
 */
public class Event extends Task {

    /**
     * Indicates the time of the event.
     */
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.DEFAULT_OUTPUT_FORMATTER
                .format(this.at) + ")";
    }
}
