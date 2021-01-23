package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate time;

    Deadline(String name, TaskType type, LocalDate time) {
        super(name, type);
        this.time = time;
    }

    Deadline(String name, TaskType type, LocalDate time, boolean done) {
        super(name, type, done);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
