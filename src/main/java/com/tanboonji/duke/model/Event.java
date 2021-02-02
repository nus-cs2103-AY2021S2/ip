package com.tanboonji.duke.model;

import com.tanboonji.duke.parser.DateParser;

import java.time.LocalDateTime;

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
