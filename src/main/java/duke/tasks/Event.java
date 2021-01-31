package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime date;

    public Event(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, FORMATTER);
    }

    public Event(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a"));
    }

    /**
     * Returns a specific String format of this Event's date to store locally when writing to file.
     *
     * @return String format of this Event's date.
     */
    public String getDateToStore() {
        return this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns String description of this Event task, identified by "[E]", along with the date of this Event.
     *
     * @return String description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }
}
