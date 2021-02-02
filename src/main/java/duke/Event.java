package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("dd MMM YYYY"));
    }
}
