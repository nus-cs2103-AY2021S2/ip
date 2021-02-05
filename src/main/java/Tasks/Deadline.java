package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime byDateTime;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    public String getByDateTimeString() {
        return this.byDateTime.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    public String getStatusString() {
        return "[D]" + super.getStatusString() + " (by: " + this.getByDateTimeString() + ")";
    }
}