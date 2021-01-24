package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public String parseDate() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") +
                " | " + description + " | " + parseDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + parseDate() + ")";
    }
}