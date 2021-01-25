package mike.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  A Task that needs to be completed before a set date
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Creates a deadline object with a description and date of the deadline
     *
     * @param description a description of the deadline
     * @param by the date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
