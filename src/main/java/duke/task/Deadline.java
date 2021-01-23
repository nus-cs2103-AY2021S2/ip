package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDateTime by;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getSaveTime() {
        return by.format(formatter);
    }

    @Override
    public String getSaveType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(formatter) + ")";
    }
}