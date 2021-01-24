import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event task that inherits Task.
 */
public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        LocalDate date = LocalDate.parse(at);
        this.at = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * A toString uniquely for Event Task
     * @return Label for Event - "E", the description of the task, followed by the timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
