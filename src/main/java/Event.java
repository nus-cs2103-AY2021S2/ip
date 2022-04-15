/**
 * Represents a category of tasks called Event, that includes the task
 * and the date as to which the event task occurs.
 */
public class Event extends Task {
    protected String at;

    /**
     * Initializes an Event task object.
     *
     * @param description refers to the task description.
     * @param at refers to the date as to which the event occurs.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
