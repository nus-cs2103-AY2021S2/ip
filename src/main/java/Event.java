public class Event extends Task {
    private final String time;

    public Event(String description, String deadline) {
        super(description);
        this.time = deadline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time + ")";
    }
}
