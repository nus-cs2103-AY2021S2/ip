package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    private String getAtString() {
        String atString = this.at.format(DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm"));
        return "(at: " + atString + ")";
    }

    public String getAt() {
        return this.at;
    }

    public String getStatusString() {
        return "[E]" + super.getStatusString() + " " + this.getAtString();
    }
}
