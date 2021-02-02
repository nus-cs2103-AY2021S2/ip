package com.tanboonji.duke.model;

import java.time.LocalDateTime;

import com.tanboonji.duke.parser.DateParser;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.toString(by) + ")";
    }
}
