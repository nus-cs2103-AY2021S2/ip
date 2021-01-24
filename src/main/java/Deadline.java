import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task that inherits Task.
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        LocalDate date = LocalDate.parse(by);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * A toString uniquely for Deadline Task
     * @return Label for Deadline - "D", the description of the task, followed by the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
