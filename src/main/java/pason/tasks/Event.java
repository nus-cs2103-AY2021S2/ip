package pason.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDate at;
    protected String extra;

    public Event(String description, LocalDate at, String extra) {
        super(description);
        this.at = at;
        this.extra = extra;
    }

    public String toFileFormat() {
        return "E | " + super.toFileFormat()
                + " | " + this.at + (this.extra != null ? " " + this.extra : "");
    }

    public String toString() {
        return "[E]" + super.toString() + " "
                + "(at: " + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + (this.extra != null ? " " + this.extra : "") + ")";
    }
}
