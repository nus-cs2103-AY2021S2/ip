/**
 * Represents an Event task that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected String at;

    /**
     * Creates an event instance.
     *
     * @param description String describing the event
     * @param at          String containing the event time
     */
    public Event(String description, String at) {
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
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}