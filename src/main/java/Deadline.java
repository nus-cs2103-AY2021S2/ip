import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Implementation for tasks with a specified end-date.
 */

public class Deadline extends Task {
    private final java.time.LocalDate lastDate;
    public Deadline(String taskName, java.time.LocalDate lastDate) {
        super(taskName);
        this.lastDate = lastDate;
    }
    @Override
    public String toString() {
        String dayOfWeek = this.lastDate.getDayOfWeek().toString().toLowerCase();
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        String date = this.lastDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + dayOfWeek + ", " + date + ")";
    }
}
