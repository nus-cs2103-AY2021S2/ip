import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Deadline.
 * A Deadline is represented by a name in the form of a String,
 * and the date by which the task should be completed, in the form
 * of a LocalDate.
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline with the description and date by which the task should be completed.
     * @param name Description of the Deadline.
     * @param by The date by which the task should be completed.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Parses the date in the format of "MMM dd yyyy".
     * @param date Date to be parsed.
     * @return A String representing the date in the new format.
     */
    public String parseDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate(by) + ")";
    }
}
