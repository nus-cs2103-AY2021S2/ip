import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), Parser.localDateToString(eventTime));
    }
}
