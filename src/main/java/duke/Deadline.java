package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task with a deadline to complete.
 */
public class Deadline extends duke.Task {
    private String by;
    private LocalDate dateOfDeadline;

    /**
     * Constructor.
     *
     * @param description description of the task
     * @param by the string containing a date of the deadline.
     */

    public Deadline(String description, String by) {
        super(description, "D");
        String dateString = Parser.extractDate(by);
        if (!dateString.equals("")) {
            this.dateOfDeadline = Parser.parseDate(dateString);
            this.by = by;
        } else {
            throw new IllegalArgumentException("Sorry Unable to Parse date for Deadline. "
                    + "Did you try to do it in yyyy-mm-dd format?");
        }
    }

    /**
     * Gets the string representation of the task, with all dates converted to format MMM d yyyy.
     *
     * @return string representation of the task.
     */

    @Override
    public String toString() {
        String dateString = Parser.extractDate(by);
        String convertedDateString = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String modifiedBy = by.replaceAll(dateString, convertedDateString);
        return super.toString() + " (by: " + modifiedBy + ")";
    }

    /**
     * Gets the string representation used to save the task in the hard disk.
     *
     * @return string representation to be saved in the hard disk.
     */

    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + by;
    }

}
