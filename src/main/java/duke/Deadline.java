package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for deadline tasks
 */
public class Deadline extends Task {

    protected String date;
    protected LocalDate formattedDate;

    /**
     * Constructor for deadline class.
     * Initializes a deadline with specified description.
     *
     * @param description description of the deadline task
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.formattedDate = LocalDate.parse(date);
        this.type = 'D';
    }

    public String getDate() {
        return this.date;
    }

    public String getFormattedDate() {
        return formattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDate() + ")";
    }
}
