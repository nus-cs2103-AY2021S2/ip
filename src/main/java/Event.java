import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an Event task with a date and time.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Creates a new Event.
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String saveTask() {
        return "E" + super.saveTask() + " |" + at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
