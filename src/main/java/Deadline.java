import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * tasks with a deadline (need to be done before a date/time)
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * @param title the title of the deadline task
     * @param deadline the deadline of the deadline task
     */
    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * @return a string describing the deadline task
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}