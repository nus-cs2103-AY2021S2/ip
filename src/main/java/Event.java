public class Event extends Task {
    private String eventTiming;

    public Event(String desc, String eventTiming) {
        super(desc);
        this.eventTiming = eventTiming;
    }

    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (at: " + eventTiming + ")";
    }
}
