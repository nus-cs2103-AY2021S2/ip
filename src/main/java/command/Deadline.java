package command;

import classes.Task;

import java.util.Date;

/**
 * Deadline inherits from Task. It encapsulates a deadline with an additional String by.
 * Deadline is specified by [D].
 */

public class Deadline extends Task {
    protected Date by;

    /**
     * Constructor method.
     * @param description User input description of deadline.
     * @param by Date and time by which the deadline is to take place.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Method to retrieve the date of the deadline.
     * @return a Date object of the deadline.
     */
    public Date getBy() {
        return this.by;
    }

    /**
     * Method to return a formatted version of user's input.
     * @return Formatted String of user's input.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
