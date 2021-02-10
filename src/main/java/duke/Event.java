package duke;

/**
 * The Event class inherits from Task and
 * has an "at" attribute
 *
 * @author  Justin Gnoh
 * @version 1.0
 * @since   2021-02-06
 */
public class Event extends Task {
    protected String at;

    /**
     * This creates an Event task with description and "at" attribute.
     *
     * @param name This is the Event description
     * @param at This is the location of the Event
     */
    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    /**
     * This creates an Event task with description, "at" attribute
     * and completion status.
     *
     * @param name This is the Event description
     * @param at This is the location of the Event
     * @param isDone This is the completion status of the Event
     */
    public Event(String name, String at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
