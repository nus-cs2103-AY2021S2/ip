package duke.tasks;

import duke.utils.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime date;

    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDateTime.parse(date, formatter);
    }

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a"));
    }

    public String getDateToStore() {
        return this.date.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
