package main.java;

import java.util.Date;

/**
 * Deadline inherits from Task. It encapsulates a deadline with an additional String by.
 * Deadline is specified by [D].
 */

public class Deadline extends Task {
    protected Date by;
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public Date getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
