public class Event extends Task {
    protected String at;

    /**
     * Constructor of the Event class.
     * @param description A brief description of the event.
     * @param at The date/time of the event.
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
