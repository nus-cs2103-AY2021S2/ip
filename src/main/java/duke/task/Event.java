package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String message, LocalDate date) {
        super("E", message, date);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
