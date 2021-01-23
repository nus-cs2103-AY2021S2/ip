import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String parseDate() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") +
                " | " + description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + parseDate() + ")";
    }
}