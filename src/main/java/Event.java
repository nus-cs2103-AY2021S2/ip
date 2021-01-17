/**
 * Event is a type of Task that includes a time.
 * Note: Date/time are treated as Strings, so any formats are accepted.
 */
public class Event extends Task {

    /**
     * Indicates the time of the event.
     */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
