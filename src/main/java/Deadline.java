import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime date;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.date = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

}
