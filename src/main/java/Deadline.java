import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String name, LocalDate by) throws DukeException {
        super(name);
        this.by = by;
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by:" + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
