import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(int isDone, String description, LocalDate by) {
        super('D', isDone, description);
        this.by = by;
    }

    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
