import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String byString;
    protected LocalDate byLocalDate;

    public Deadline(String description, String by) {
        super(description);
        byString = by;
        try {
            byLocalDate = LocalDate.parse(by);
        } catch (Exception e) {
            byLocalDate = LocalDate.parse("2999-12-31");
        }
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