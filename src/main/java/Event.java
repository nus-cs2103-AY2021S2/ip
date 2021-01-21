
/**
 * Encapsulates information and state of a Event.
 * For tasks that lasts for a certain period at certain location.
 */
public class Event extends Task {
    /** Location of event. */
    protected String at;

    /**
     * Initialises a new Event with text description.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", 
            this.getStatusIcon(), super.toString(), this.at);
    }
}
