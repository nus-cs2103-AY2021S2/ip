package pason.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initialises the Deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats Deadline into text format for file saving.
     */
    public String toFileFormat() {
        return "D | " + super.toFileFormat()
                + " | " + this.by;
    }

    /**
     * Returns the date.
     */
    public String getDate() {
        return this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Formats Deadline to textual form.
     */
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma")) + ")";
    }
}
