import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate eventTime;

    public Event(String description, LocalDate eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + "(at: " + this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
