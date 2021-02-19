package justin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class creates a Deadline class
 * that extends from Task class
 *
 * @author Goh Wei Kiat aka github : mrweikiat
 * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
 */

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * This method creates a new Deadline class
     *
     * @param description of the given task
     * @param by the date given by user
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}