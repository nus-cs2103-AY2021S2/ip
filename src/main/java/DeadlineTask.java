import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    protected String deadline;

    /**
     * Creates a new instance of <code>DeadlineTask</code>.
     *
     * @param description Description of deadline task.
     * @param deadline Deadline of deadline task.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns date of deadline.
     *
     * @return Date of deadline.
     */
    public LocalDate getDeadlineDate() throws DateTimeParseException {
        String[] deadlineArr = this.deadline.split(" ");
        LocalDate deadlineDate = LocalDate.parse(deadlineArr[0]);
        return deadlineDate;
    }

    /**
     * Returns time of deadline.
     *
     * @return Time of deadline.
     */
    public LocalTime getDeadlineTime() throws DateTimeParseException {
        String[] deadlineArr = this.deadline.split(" ");
        LocalTime deadlineTime = LocalTime.parse(deadlineArr[1]);
        return deadlineTime;
    }

    /**
     * Returns String representation of deadline task.
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + this.description
                + " (by: " + this.getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.getDeadlineTime().format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
