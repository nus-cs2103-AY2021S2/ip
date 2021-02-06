import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event task that inherits Task.
 */
public class Event extends Task {

    /**
     * Date/Timing information for task to be carried out at.
     */
    protected String at;

    public Event(String description, String at, boolean isDone, boolean existing) {
        super(description, isDone);
        if (existing) {
            this.at = at;
        } else {
            LocalDate date = LocalDate.parse(at);
            this.at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }

    /**
     * A toString uniquely for Event Task.
     *
     * @return Label for Event - "E", the description of the task, followed by the timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
