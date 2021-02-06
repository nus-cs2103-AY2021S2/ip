package yoda.task;

/**
 * Event class that is a specialised version of the Task class and extends the Task class.
 */
public class Event extends Task {
    /**
     * Creates an Event object.
     * @param description Description of Event object.
     * @param at When the Event object is occurring.
     */
    public Event(String description, String at) {
        super(description);
        setDateTime(at);
    }

    /**
     * Formats the Event information to be user-readable.
     * @return User-readable format of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "at " + formatDateTime();
    }
}
