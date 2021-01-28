package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, int status, LocalDateTime at) {
        super(description,status);
        this.at = at;
    }

    @Override
    public String toTxt() {
        return "E " + super.toTxt() + " | " + at.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")) + ")";
    }
}
