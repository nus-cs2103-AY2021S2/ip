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
            String convertedDateString = dateOfDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));  // convert the format
            this.by = by.replaceAll(dateString, convertedDateString);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "[" + this.getLetterCode() + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + by;
    }

}
