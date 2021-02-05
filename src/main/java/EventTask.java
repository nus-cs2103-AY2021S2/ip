/**
 * Represents a event task.
 */
public class EventTask extends Task {

    protected String eventDate;

    /**
     * Creates a new instance of <code>EventTask</code>.
     * @param description Description of event task.
     */
    public EventTask(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Returns String representation of event task.
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + this.description
                + " (at: " + this.eventDate + ")";
    }
}