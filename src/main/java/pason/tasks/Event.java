package pason.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDate at;
    protected String extra;

    /**
     * Initialises the Event.
     */
    public Event(String description, LocalDate at, String extra) {
        super(description);
        this.at = at;
        this.extra = extra;
    }

    /**
     * Formats Event into text format for file saving.
     */
    public String toFileFormat() {
        return "E | " + super.toFileFormat()
                + " | " + this.at + (this.extra != null ? " " + this.extra : "");
    }

    /**
     * Returns the date.
     */
    public String getDate() {
        return this.at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Formats Event to textual form.
     */
    public String toString() {
        return "[E]" + super.toString() + " "
                + "(at: " + this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + (this.extra != null ? " " + this.extra : "") + ")";
    }
}
