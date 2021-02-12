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
     * Constructor. Note that it parses the first occurrence of the string "yyyy-M-d" that occurs
     * into a LocalDate Object and ignores any other dates inside.
     *
     * @param description description of the task
     * @param by the string containing a date of the deadline in the format "yyyy-M-d"
     */
    private static final String DEADLINE_FORMAT_ERROR_MESSAGE =
            "Sorry Unable to Parse date for Deadline. "
            + "Did you put your date in yyyy-mm-dd format?";

    public Deadline(String description, String by) throws DukeParseException {
        super(description, "D");
        String dateString = DateParser.extractDate(by);
        if (!dateString.equals("")) {
            this.dateOfDeadline = DateParser.parseDate(dateString);
        } else {
            throw new DukeParseException(DEADLINE_FORMAT_ERROR_MESSAGE);
        }
        this.by = by;
    }

    /**
     * Gets the string representation of the task, with all dates converted to format MMM d yyyy.
     *
     * @return string representation of the task.
     */

    @Override
    public String toString() {
        String dateString = DateParser.extractDate(by);
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
