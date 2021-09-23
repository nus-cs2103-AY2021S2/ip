package dbot.task;

import dbot.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An implementation of the Task class that represents Deadline Tasks.
 *
 * Deadline tasks are tasks that take a description and a /by parameter which specifies the date of the Deadline.
 *
 * The Deadline class is visually represented with the prefix: [D]
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initializes a Deadline Task with the provided description String and date.
     *
     * @param description A String containing the Deadline description.
     * @param by A LocalDate that represents the date of the Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the exact String required to construct a replicate of this Deadline Task.
     *
     * @return A String that can be used to construct a replicate Deadline Task.
     */
    @Override
    public String getFullDescription() {
        return getDescription() + " /by " + by;
    }

    /**
     * Returns this Deadline Task with its representative prefix: [D].
     *
     * @return A String representing this Deadline Task with the prefix [D].
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
