public class Event extends Task {

    protected String at;

    /**
     * Constructor to create an event.
     *
     * @param description task
     * @param at time
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
