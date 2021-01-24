package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", 
            super.getStatusIcon(), super.toString(), at.format(DateTimeFormatter.ofPattern("d MMM yyyy - HHmm")));
    }
}
