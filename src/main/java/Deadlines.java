import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    private LocalDate by;

    public Deadlines(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public Deadlines(String name, LocalDate by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
