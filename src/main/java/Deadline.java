import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor of the Deadline class
     * @param description A brief description of the deadline.
     */

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        int dayOfMonth = deadline.getDayOfMonth();
        Month month = deadline.getMonth();
        int year = deadline.getYear();
        LocalTime time = deadline.toLocalTime();
        String twelveHrTime = time.format(DateTimeFormatter.ofPattern("h:mm a"));
        return "[D]" + super.toString() + " (by: " + month.toString()
                + " " + dayOfMonth + " " + year + " " + twelveHrTime + ")";
    }
}
