/**
 * Creates task objects categorised as an "event" task
 *
 * @author Amanda Ang
 */
public class Event extends Task {

    protected String at;

    /**
     * Construct an Event object
     *
     * @param description the description of the Event task
     * @param at the
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + at;
    }
}
