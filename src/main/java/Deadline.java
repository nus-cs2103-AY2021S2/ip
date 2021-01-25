import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")) + ")";
    }
}
