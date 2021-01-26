package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private final LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", 
            super.getStatusIcon(), super.toString(), 
            at.format(DateTimeFormatter.ofPattern("d MMM yyyy - HHmm")));
    }
}
