import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime eventTime;

    public EventTask(String name) {
        super(name);
    }

    public EventTask(String name, String eventTime) {
        super(name);
        this.setEventTime(eventTime);
    }

    public void setEventTime(String eventTime) {
        this.eventTime = LocalDateTime.parse(eventTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + (this.eventTime != null ? String.format(" (at: %s)", this.eventTime) : "");
    }
}
