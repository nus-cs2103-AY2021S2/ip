public class Event extends AbstractTask {
    protected String at;

    public Event(String description, String at) throws DukeEmptyDescriptionException {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}