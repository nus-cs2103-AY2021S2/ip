import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    public LocalDate by;

    public Deadline(String task, String by) {
        super(task);
        by = by.replaceFirst(" ", "");
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString()
                + "(by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
