import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    LocalDate by;
    LocalTime time;

    public Deadline(String description, LocalDate by, LocalTime time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + " " + time + ")";
    }
}