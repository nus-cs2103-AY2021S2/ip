import java.time.format.DateTimeFormatter;

/**
 * Implementation for tasks with a given start-time and end-time.
 */

public class Event extends Task {
    private final java.time.LocalDate startAndEnd;
    public Event(String taskName, java.time.LocalDate startAndEnd) {
        super(taskName);
        this.startAndEnd = startAndEnd;
    }

    @Override
    public String toString() {
        String dayOfWeek = this.startAndEnd.getDayOfWeek().toString().toLowerCase();
        dayOfWeek = dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
        String date = this.startAndEnd.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (at: " + dayOfWeek + ", " + date + ")";
    }
}
