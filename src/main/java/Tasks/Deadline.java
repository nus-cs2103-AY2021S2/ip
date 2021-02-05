package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String getByString() {
        return this.by.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
    }

    public String getStatusString() {
        return "[D]" + super.getStatusString() + " (by: " + this.getByString() + ")";
    }
}