import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Creates an event instance.
     *
     * @param description String describing the event
     * @param at          String containing the event time
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a String which gives information about the event.
     *
     * @return A String containing information about the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a String which gives information about the event in the
     * format to be stored in a file.
     *
     * @return A String containing information about the event.
     */
    @Override
    public String fileString() {
        int statusNum = this.getStatus() ? 1 : 0;
        return "E | " + statusNum + " | " + this.getDescription() + " | " + this.at;
    }
}
