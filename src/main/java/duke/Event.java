package duke;

/**
 * Represents an event that is occurring at a given date/time.
 */
public class Event extends Task {
    private final String at;

    /**
     * Creates a new Event object with a description and date/time of occurrence.
     *
     * @param description Description of the event.
     * @param at          Date/time that the event is occurring at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getTaskCommand() {
        return "e " + getDescription() + " /at " + at;
    }
}
