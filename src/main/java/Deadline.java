import exceptions.DukeExceptionIllegalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeExceptionIllegalDate {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) throws DukeExceptionIllegalDate {
        super(description, isDone);
        this.by = parseDate(by);
    }

    // Enforce specific datetime
    public LocalDateTime parseDate(String by) throws DukeExceptionIllegalDate {
        try {
            return LocalDateTime.parse(by, fmt);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionIllegalDate("TODO: Wrong date.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outfmt) + ")";
    }

    public String toFileString() {
        return "D | " + ((isDone) ? 1 : 0) + " | " + description + " | " + by.format(fmt);
    }
}
