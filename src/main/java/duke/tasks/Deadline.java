package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task{
    private static final String TYPE = "DEADLINE";
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TYPE);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by:" + by.format(super.DATE_TIME_FORMATTER) + ")";
    }
 }
