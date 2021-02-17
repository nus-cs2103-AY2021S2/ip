package com.nus.duke.data;

import java.time.LocalDateTime;

import com.nus.duke.parser.DateParser;

/**
 * Event is a type of Task that includes a time.
 */
public class Event extends Task {

    /**
     * Indicates the time of the event.
     */
    protected final LocalDateTime at;

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
