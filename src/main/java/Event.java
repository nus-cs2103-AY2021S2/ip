public class Event extends Task {
    private String eventTime;

    public Event(String desc, String eventTime) {
        super(desc);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventTime + ")";
    }
}
