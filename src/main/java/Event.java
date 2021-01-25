import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This Event class extends the Task class and is used to
 * represent a single event that users can add to their list.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(boolean isDone, String description, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return "Event`" + this.isDone + "`" + this.description + "`" + this.at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " +
                at.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}
