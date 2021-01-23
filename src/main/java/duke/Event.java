package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate time;

    Event(String name, TaskType type, LocalDate time) {
        super(name, type);
        this.time = time;
    }

    Event(String name, TaskType type, LocalDate time, boolean done) {
        super(name, type, done);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " +
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
