package duke.task;

import duke.parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Parser.parseDateTimeToString(time) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + description +
                " | " + Parser.parseDateTimeToString(time);
    }
}
