import java.time.LocalDateTime;

/**
 * Represents a task of type Deadline.
 * A Deadline is represented by a name in the form of a String,
 * and the date and time by which the task should be completed, in the form
 * of a LocalDateTime.
 */

public class Deadline extends Task {

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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeHandler.parseLocalDateTimeIntoString(by) + ") "
                + (isThereTag ? getTag() : "");
    }
}
