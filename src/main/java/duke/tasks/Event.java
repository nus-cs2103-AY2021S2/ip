package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime atDateTime;

    public Event(String description, LocalDateTime atDateTime) {
        super(description);
        this.atDateTime = atDateTime;
    }

    public String getAtDateTimeString() {
        return this.atDateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    public String getStatusString() {
        return "[E]" + super.getStatusString() + " (at: " + this.getAtDateTimeString() + ")";
    }
}
