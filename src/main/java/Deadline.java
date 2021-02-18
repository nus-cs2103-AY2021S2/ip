import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Deadline.
 * A Deadline is represented by a name in the form of a String,
 * and the date and time by which the task should be completed, in the form
 * of a LocalDateTime.
 */

public class Deadline extends Task {

    static final String DATE_TIME_FORMAT = "MMM dd yyyy h:mm a";
    protected LocalDateTime by;

    /**
     * Creates a Deadline with the description and date and time by which the task should be completed.
     *
     * @param name Description of the Deadline.
     * @param by The date and time by which the task should be completed.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Parses the date and time in the format of "MMM dd yyyy h:mm a".
     *
     * @param details Date and time to be parsed.
     * @return A String representing the date and time in the new format.
     */
    public String parseDeadlineDateTime(LocalDateTime details) {
        return details.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDeadlineDateTime(by) + ") " + (isThereTag ? getTag() : "");
    }
}
