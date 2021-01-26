import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String extractDateTime(String by) {
        String[] temp = by.split(" ");
        LocalDate date = LocalDate.parse(temp[0]);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + temp[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + extractDateTime(by) + ")";
    }
}
