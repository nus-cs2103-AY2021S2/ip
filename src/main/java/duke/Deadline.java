package duke;

import java.time.LocalDate;

/**
 * The Deadline class has methods for a Deadline object
 * Inherits from the Task.
 */

public class Deadline extends Task {

    private LocalDate deadline;

    /**
     * Constructor for new Deadline
     *
     * @param description of the new Deadline
     * @param deadline from new Deadline
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * representates a Deadline object as a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }
}
