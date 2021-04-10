package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventDate;

    public Event(LocalDate eventDate, String eventDetail) {
        super(eventDetail);
        this.eventDate = eventDate;
    }

    public LocalDate getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        return "[E] | " + super.toString() + " | at: " + eventDate.format(DateTimeFormatter.ofPattern("dd MMM YYYY"));
    }
}
