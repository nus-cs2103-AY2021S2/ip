package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task with a deadline to complete.
 */
public class Deadline extends Task {
    protected String by;
    private LocalDate dateOfDeadline;

    public Deadline(String description,String by) {
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

    @Override
    public String toString() {
        String dateString = Parser.extractDate(by);
        String convertedDateString = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));  // convert the format
        String modified_by = by.replaceAll(dateString, convertedDateString);
        return "[" + this.getLetterCode() + "]" + super.toString() + " (by: " + modified_by + ")";
    }


    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + by;
    }

}
