import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class extends the Task class and is used to
 * represent a single item with a deadline that users can add to their list.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Overloaded constructor for Deadline class.
     * @param description description of task.
     * @param by deadline of task with deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor for Deadline class.
     * @param isDone whether the task is already marked off as done.
     * @param description description of task.
     * @param by deadline of task with deadline.
     */
    public Deadline(boolean isDone, String description, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Generates description to be saved in file for later retrieval.
     *
     * @return record of event to be saved in file.
     */
    @Override
    public String getDescription() {
        return "Deadline`" + this.isDone + "`" + this.description + "`" + this.by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}
