package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline, a sub-class of Task.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor of the Deadline class.
     *
     * @param description A brief description of the Deadline.
     * @param deadline Date and Time of the Deadline.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, "0");
        this.deadline = deadline;
    }

    /**
     * Constructor of the Deadline class.
     *
     * @param description A brief description of the deadline.
     * @param isDone "0" if task is not done. "1" if task is done.
     */
    public Deadline(String description, String isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the Date and Time of the Deadline.
     *
     * @return A String representing the deadline in the following format: "YYYY-MM-DD HHmm".
     */
    public String getDateTime() {
        return deadline.toLocalDate().toString()
                + " " + deadline.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Returns a String representation of the Deadline to be displayed to the user.
     * Shows the description, date and time of the Deadline, as well as whether it is done.
     *
     * @return A String representing the Deadline.
     */
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
