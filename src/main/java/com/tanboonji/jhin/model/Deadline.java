package com.tanboonji.jhin.model;

import java.time.LocalDateTime;

import com.tanboonji.jhin.parser.DateParser;

/**
 * The Deadline class stores information about a deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Class constructor specifying the description and deadline of task.
     *
     * @param description Task description.
     * @param by Task deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.toString(by) + ")";
    }
}
