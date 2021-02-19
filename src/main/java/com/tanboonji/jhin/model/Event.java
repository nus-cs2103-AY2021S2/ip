package com.tanboonji.jhin.model;

import java.time.LocalDateTime;

import com.tanboonji.jhin.parser.DateParser;

/**
 * The Event class stores information about an event task.
 */
public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Class constructor specifying the description and event date of task.
     *
     * @param description Task description.
     * @param at Task event date.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.toString(at) + ")";
    }
}
