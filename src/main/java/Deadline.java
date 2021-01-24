import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String name, String by) {
        super(name);
        this.by = LocalDate.parse(by);
        this.cat = 'D';
    }

    public LocalDate getDeadline() {
        return this.by;
    }

    public String getFormattedDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getFormattedDeadline() + ")";
    }

}

