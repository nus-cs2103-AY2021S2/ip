package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDate by, boolean done) {
        super(name, done);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
