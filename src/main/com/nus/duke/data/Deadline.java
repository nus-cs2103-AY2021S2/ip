package com.nus.duke.data;

/**
 * Deadline is a type of Task that needs to be done before a specific date/time.
 * Note: Date/time are treated as Strings, so any formats are accepted.
 */
public class Deadline extends Task {

    /**
     * Indicates the end date/time of the deadline.
     */
    protected final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
