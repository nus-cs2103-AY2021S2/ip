package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime startTime;

    public Event(String name, LocalDateTime eventStartTime) {
        super(name);
        startTime = eventStartTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return "E " + super.toFileString() + " | "
                + startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
