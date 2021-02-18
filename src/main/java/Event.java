import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Constructs event object.
     *
     * @param description Description of the event.
     * @param at Date and time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    private String formatDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    private String formatDateForSaving() {
        return this.at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Formats string to be stored.
     *
     * @return Formatted String.
     */
    @Override
    public String getSaveString() {
        return "E" + " | " + (isDone ? "1" : "0") + " | "
                + this.description + " | " + formatDateForSaving();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + this.formatDate() + ")";
    }
}
