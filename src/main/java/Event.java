import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event task that inherits Task.
 */
public class Event extends Task {

    /**
     * Date/Timing information for task to be carried out at.
     */
    protected String eventDate;

    /**
     * Constructor for Event.
     *
     * @param description The description of the task.
     * @param eventDate   Date of the task.
     * @param isDone      Indicator for whether the task has been completed.
     * @param taskExists  Indicator for whether the task is already in the task list.
     */
    public Event(String description, String eventDate, boolean isDone, boolean taskExists) {
        super(description, isDone);
        if (taskExists) {
            this.eventDate = eventDate;
        } else {
            LocalDate date = LocalDate.parse(eventDate);
            this.eventDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

    }

    /**
     * A toString uniquely for Event Task.
     *
     * @return Label for Event - "E", the description of the task, followed by the timing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
