package task;

/**
 * A task type Event object.
 */
public class Event extends Task {
    protected String at;

    /**
     * Instantiates a new event task.
     *
     * @param description the event description
     * @param at          the at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Gets the events at date.
     *
     * @return the at date
     */
    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "EVENT \u00BB " + super.description + " (at: " + at + ") " + super.getStatusIcon();
    }
}
