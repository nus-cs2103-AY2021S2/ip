import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a child class of Task, containing additional
 * support for an endDate.
 */
public class Event extends Task {
    private final LocalDate startEndDate;

    /**
     * Constructor for Event class.
     * @param id id of task
     * @param taskName name of task
     * @param status task completion status
     * @param startEndDate start to end datetime for given task
     */
    public Event(int id, String taskName, String status, LocalDate startEndDate) {
        super(id, taskName, status, "E");
        this.startEndDate = startEndDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.startEndDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
