import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected String byString;
    protected LocalDate byLocalDate;

    public Deadline(String description, String by) {
        super(description);
        byString = by;
        byLocalDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        String byDateFormatted = byLocalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.doneToString() + " (by: " + byDateFormatted + ")";
    }

    public String getBy() {
        return byString;
    }
}