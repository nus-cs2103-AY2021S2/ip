import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    private String formatDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }
}