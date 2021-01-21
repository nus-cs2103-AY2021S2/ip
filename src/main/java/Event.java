public class Event extends AbstractTask {
    protected String at;

    /*
     * Constructs an event task using the description and the event timing
     */
    public Event(String description, String at) throws DukeEmptyDescriptionException {
        super(description);
        this.at = at;
    }

    /*
     * Return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}