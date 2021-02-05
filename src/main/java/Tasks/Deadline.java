package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    private String getByString() {
        String byString = this.by.format(DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm"));
        return "(by: " + byString + ")";
    }

    public String getBy() {
        return this.by;
    }

    public String getStatusString() {
        return "[D]" + super.getStatusString() + " " + this.getByString();
    }
}
