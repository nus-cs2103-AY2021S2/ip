import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task Object that has a certain deadline to be met.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Constructor for this Deadline object.
     * @param   description  Task Description.
     * @param   by           Due date of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = parseDate(by);
    }


    private LocalDate parseDate(String str) {
        return LocalDate.parse(str);
    }

    /**
     * Converts information about the task to be saved to hard disk.
     * @return string containing information about the task.
     */
    @Override
    public String save() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline Task.
     * @return the type of task, task completion status, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }
}
