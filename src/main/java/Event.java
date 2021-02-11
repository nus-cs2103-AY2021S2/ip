import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event type of Task.
 * Inherits from the superclass Task.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructor to create an Event Task.
     * Keeps track of the date the event is at.
     * @param name Name of the Event.
     * @param at Date of the Event.
     */
    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    /**
     * Constructor used when retrieving data from hard drive.
     * @param name Name of the Event.
     * @param at Date of the Event.
     * @param done If the Event is done or not.
     */
    public Event(String name, LocalDate at, boolean done) {
        super(name, done);
        this.at = at;
    }

    /**
     * Getter for the date of the Event.
     * @return The date of the Event.
     */
    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
