public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, "Event");
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + at + ")";
    }
}
