package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public String getAtString() {
        return this.at.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    public String getStatusString() {
        return "[E]" + super.getStatusString() + " (at: " + this.getAtString() + ")";
    }
}