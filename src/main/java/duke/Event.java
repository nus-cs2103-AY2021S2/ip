package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
        isDone = false;
    }

    @Override
    public String getDate() {
        return " (at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getDate() + "\n";
    }
}
