public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        this(description, at, false);
    }
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toFileString() {
        return "E | " + ((isDone) ? 1 : 0) + " | " + description + " | " + at;
    }
}
