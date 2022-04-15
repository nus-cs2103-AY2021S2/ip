package Duke.Task;

/**
 * An event is a task that start at a specific time and ends at a specific time
 */
public class Event extends Task {
    protected String at;

    /**
     * The Deadline class constructor has 2 parameters:
     * the description about a specific deadline and the time that the event occurs.
     * @param description The description about the event.
     * @param at The time that the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    /**
     * Gets the time that the event occurs.
     * @return The time that the event occurs.
     */
    @Override
    public String getTime() {
        return " | " + at;
    }

    /**
     * Gets the string representation for this Event object.
     * @return The string representation for this object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.trim() + ")";
    }
}
