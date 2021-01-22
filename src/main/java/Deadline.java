import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a child class of Task, containing additional
 * support for an endDate.
 */
public class Deadline extends Task {
    private final LocalDate endDate;

    /**
     * Constructor for Deadline class.
     * @param id id of task
     * @param taskName name of task
     * @param status task completion status
     * @param endDate end datetime for given task
     */
    public Deadline(int id, String taskName, String status, LocalDate endDate) {
        super(id, taskName, status, "D");
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}