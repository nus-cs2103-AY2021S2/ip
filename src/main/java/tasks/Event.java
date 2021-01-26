package tasks;

import utils.DateFormatter;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateFormatter.decodeDate(at) + ")";
    }
}
