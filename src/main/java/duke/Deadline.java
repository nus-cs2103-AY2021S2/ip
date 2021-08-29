package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a type of Task that
 * needs to be done by a set date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Class constructor.
     *
     * @param description the details of the deadline.
     * @param by          the date and time the deadline needs to be done by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of this Deadline.
     *
     * @return a String representation of this Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma"))
                + ")";
    }
}
