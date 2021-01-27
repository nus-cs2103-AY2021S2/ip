import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, LocalDate by) throws DukeException {
        super(name);
        this.by = by;
    }

    public Deadline(String name, LocalDate by, boolean done) {
        super(name, done);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
