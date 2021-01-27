import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    String by;
    String time;

    public Deadline(String description, String by, String time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + " " + time + ")";
    }
}