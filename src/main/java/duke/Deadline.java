package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for deadline tasks
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate localBy;

    /**
     * Constructor for deadline class.
     * Initializes a deadline with specified description.
     *
     * @param description description of the deadline task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.localBy = LocalDate.parse(by);
        this.type = 'D';
    }

    public String getBy() {
        return this.by;
    }

    public String getFormattedBy() {
        return localBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedBy() + ")";
    }
}
