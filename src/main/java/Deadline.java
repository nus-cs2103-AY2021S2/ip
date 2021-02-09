import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public LocalDate by;

    public Deadline(String description, String by) {
        super(description, "Deadline");
        this.by = LocalDate.parse(by);
    }

    public String getBy() {
        return by.toString();
    }

    @Override
    public String toString() {
        String formatBy = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + "(by: " + formatBy + ")";
    }
}