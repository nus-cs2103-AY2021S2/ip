import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String message, LocalDate date) {
        super("D", message, date);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
