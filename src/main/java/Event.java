public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + this.at;
    }

    public String toString() {
        return "[E]" + super.toString() + " " + "(at: " + this.at + ")";
    }
}
