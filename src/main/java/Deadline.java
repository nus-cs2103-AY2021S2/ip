import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task Object that has a certain deadline to be met.
 */
public class Deadline extends Task {
    protected String byDeadline;
    protected LocalDate DeadlineDate;

    /**
     * Constructor for this Deadline object.
     *
     * @param   description  Task Description.
     * @param   byDeadline   Due date of task.
     */
    public Deadline(String description, String byDeadline) {
        super(description);
        this.byDeadline = byDeadline;
        this.DeadlineDate = parseDate(byDeadline);
    }

    /**
     * Parses the date from a string into a LocalDate object.
     *
     * @param str String of the deadline date of the task.
     * @return LocalDate object representing the deadline of the task.
     */
    private LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    /**
     * Converts information about the task to be saved to hard disk.
     *
     * @return string containing information about the task.
     */
    @Override
    public String save() {
        return "[D]" + super.toString() + "(by: " + byDeadline + ")";
    }

    /**
     * Returns a string representation of the Deadline Task.
     *
     * @return the type of task, task completion status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DeadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }
}
