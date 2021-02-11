import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task that inherits Task.
 */
public class Deadline extends Task {

    /**
     * Date/Timing information for task to be completed by.
     */
    protected String dueDate;

    public Deadline(String description, String dueDate, boolean isDone, boolean taskExists) {
        super(description, isDone);
        if (taskExists) {
            this.dueDate = dueDate;
        } else {
            LocalDate date = LocalDate.parse(dueDate);
            this.dueDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }

    /**
     * A toString unique for Deadline Task.
     *
     * @return Label for Deadline - "D", the description of the task, followed by the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
