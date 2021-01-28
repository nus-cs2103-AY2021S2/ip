package seedu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + start.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")) + " to "
                + end.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")) + ")";
    }
}
