package duke.tasks;

import duke.exceptions.DukeDateParseException;
import duke.parser.DateParser;

import java.time.format.DateTimeFormatter;

/**
 * A Task with a deadline to complete.
 */
public class Deadline extends Task {

    private String by;
    private String byToPrint; //all dates are converted to more readable form for printing.

    /**
     * Constructor. Note that it parses the first occurrence of the string "yyyy-M-d" that occurs
     * into a LocalDate Object and ignores any other dates inside.
     *
     * @param description description of the task
     * @param by the string containing a date of the deadline in the format "yyyy-M-d"
     */


    public Deadline(String description, String by) throws DukeDateParseException{
        super(description, "D");
        this.localDate = DateParser.parseLocalDate(by);
        this.by = by;
        this.byToPrint = DateParser.replaceDate(by,localDate);
    }

    /**
     * Gets the string representation of the task, with all dates converted to format MMM d yyyy.
     *
     * @return string representation of the task.
     */

    @Override
    public String toString() {
        return super.toString() + " (by: " + byToPrint + ")";
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
