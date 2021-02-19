package zeke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for deadline tasks.
 */
public class Deadline extends Task {

    protected String date;
    protected LocalDate localDate;

    /**
     * Constructor for deadline class.
     * Initializes a deadline with specified description and date.
     *
     * @param description description of the deadline task.
     * @param date date of the deadline task.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.localDate = LocalDate.parse(date);
        this.type = 'D';
    }

    /**
     * Returns date of this deadline.
     *
     * @return the date of this deadline.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Returns the parsed LocalDate.
     *
     * @return the LocalDate of this deadline.
     */
    public LocalDate getLocalDate() {
        return this.localDate;
    }

    /**
     * Returns a date in MMM dd yyyy format.
     *
     * @return a formatted date.
     */
    public String getFormattedDateString() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getFormattedDateString() + ")";
    }
}
