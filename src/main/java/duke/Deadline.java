package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Constructor method.
     * @param description User input description of deadline
     * @param date Date of the deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Method to get the date of the deadline.
     * @return a Date object of the deadline.
     */
    public LocalDate getBy() {
        return this.date;
    }

    /**
     * Method to get the date of the deadline as a string object
     * @return a String object of the deadline.
     */
    public String getDateString() {
        assert this.date.toString().length() > 0;
        return this.date.toString();
    }

    /**
     * Method to return a Deadline object in the specified format
     * @return Formatted String of Deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
    }
}

