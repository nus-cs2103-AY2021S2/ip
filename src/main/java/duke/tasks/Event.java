package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private static final String TYPE = "EVENT";
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description, TYPE);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(at:" + at.format(super.DATE_TIME_FORMATTER) + ")";
    }
}