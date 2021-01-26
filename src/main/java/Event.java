public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s\n", Command.EVENT, super.toFileString(), this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
