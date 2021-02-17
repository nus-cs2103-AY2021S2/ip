package flamingo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an Event task with a date and time.
 */
public class Event extends Task {
    protected LocalDateTime dateAndTime;

    /**
     * Creates a new Event.
     * @param description Description of the event.
     * @param dateAndTime Date and time of the event.
     */
    public Event(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String saveTask() {
        return "E" + super.saveTask() + " |" + dateAndTime + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + dateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
