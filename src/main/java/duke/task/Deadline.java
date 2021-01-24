package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description, "0");
        this.deadline = deadline;
    }

    /**
     * Constructor of the duke.Tasks.Deadline class
     * @param description A brief description of the deadline.
     */

    public Deadline(String description, String isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String getDetails() {
        return deadline.toLocalDate().toString()
                + " " + deadline.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
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
