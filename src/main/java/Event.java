public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.doneToString() + " (at: " + at + ")";
    }

    public String getAt() {
        return this.at;
    }
}