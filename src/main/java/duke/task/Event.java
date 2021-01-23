package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    private final LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.date + ")";
    }
}
