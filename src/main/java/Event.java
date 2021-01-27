import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime eventTiming;

    public Event(String desc, String eventTiming) {
        super(desc);
        this.eventTiming = ParseDateTime.parse(eventTiming);
    }

    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (at: " + ParseDateTime.readableString(eventTiming) + ")";
    }
}
