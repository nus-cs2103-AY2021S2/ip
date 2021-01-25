package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime datetime;

    public Event(String taskName, LocalDateTime datetime) {
        super(taskName);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
