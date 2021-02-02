package com.tanboonji.duke.model;

import java.time.LocalDateTime;

import com.tanboonji.duke.parser.DateParser;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.toString(at) + ")";
    }
}
