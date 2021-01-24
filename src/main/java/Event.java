public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, "0");
        this.at = at;
    }

    /**
     * Constructor of the Event class.
     * @param description A brief description of the event.
     * @param at The date/time of the event.
     */

    public Event(String description, String isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getDetails() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
