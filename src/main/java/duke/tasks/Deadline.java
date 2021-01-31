package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, FORMATTER);
    }

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a"));
    }

    /**
     * Returns a specific String format of this Deadline's date to store locally when writing to file.
     *
     * @return String format of this Deadline's date.
     */
    public String getDateToStore() {
        return this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns String description of this Deadline task, identified by "[D]", along with the date of this Deadline.
     *
     * @return String description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
