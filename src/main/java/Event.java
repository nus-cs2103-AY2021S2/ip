import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This Event class extends the Task class and is used to
 * represent a single event that users can add to their list.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Overloaded constructor for Event class.
     *
     * @param description description of event.
     * @param at timing of event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Overloaded constructor for Event class
     *
     * @param isDone whether the event is already marked off as done.
     * @param description description of event.
     * @param at timing of event.
     */
    public Event(boolean isDone, String description, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Generates description to be saved in file for later retrieval.
     *
     * @return record of event to be saved in file.
     */
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
