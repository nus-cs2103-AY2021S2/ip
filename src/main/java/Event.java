public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    private String getAtString() {
        return "(at: " + this.at + ")";
    }

    public String getStatusString() {
        return "[E]" + super.getStatusString() + " " + this.getAtString();
    }
}
