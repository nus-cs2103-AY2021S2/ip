import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime date;

    Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a"));
    }

    public String getDateToStore() {
        return this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
