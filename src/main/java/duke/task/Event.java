package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter DATETIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.at.format(Event.DATETIME_FORMATTER));
    }
}
