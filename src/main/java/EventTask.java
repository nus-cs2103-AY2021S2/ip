import java.time.LocalDateTime;

public class EventTask extends TaskWithDateTime {
    public EventTask(String description, LocalDateTime at) {
        super(description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(DATE_TIME_FORMAT) + ")";
    }
}
