import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime d;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.d = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.d.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
