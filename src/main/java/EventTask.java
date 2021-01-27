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

    public String getEventTime() {
        return String.format(
                "%d %s %s, %s",
                this.eventTime.getDayOfMonth(),
                this.eventTime.getMonth(),
                this.eventTime.getYear(),
                this.eventTime.toLocalTime()
        );
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + (this.eventTime != null ? String.format(" (at: %s)", this.getEventTime()) : "");
    }
}
