import exceptions.DukeExceptionIllegalDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;


    public Event(String description, String at) throws DukeExceptionIllegalDate {
        this(description, at, false);
    }
    public Event(String description, String at, boolean isDone) throws DukeExceptionIllegalDate {
        super(description, isDone);
        this.at = parseDate(at);
    }

    // Enforce specific datetime
    public LocalDateTime parseDate(String at) throws DukeExceptionIllegalDate {
        try {
            return LocalDateTime.parse(at, fmt);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionIllegalDate("TODO: Wrong date.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(outfmt) + ")";
    }

    public String toFileString() {
        return "E | " + ((isDone) ? 1 : 0) + " | " + description + " | " + at;
    }
}
