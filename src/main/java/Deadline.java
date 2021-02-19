import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

/**
 * Simulates a task with a deadline.
 */
public class Deadline extends Task {

    /** Date of the deadline */
    private LocalDate deadline;

    /** Time of the deadline */
    private LocalTime deadlineTime;

    /**
     * Creates a new Deadline.
     *
     * @param details Details of the task.
     * @param deadline Date of the deadline.
     * @param deadlineTime Time of the deadline.
     */
    public Deadline(String details, LocalDate deadline, LocalTime deadlineTime) {
        super(details);
        this.deadline = deadline;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Constructor to complete a deadline that is completed.
     *
     * @param details Details of the task.
     * @param deadline Date of the deadline.
     * @param deadlineTime Time of the deadline.
     * @param indicator Denotes that task is completed regardless of boolean value passed.
     */
    public Deadline(String details, LocalDate deadline, LocalTime deadlineTime, boolean indicator) {
        super(details, indicator);
        this.deadline = deadline;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Completes the Deadline.
     *
     * @return New completed Deadline with the same details.
     */
    public Deadline completeTask() {
        return new Deadline(this.getTask_details(), deadline, deadlineTime, true);
    }

    /**
     * Returns a String describing the Deadline as well as its completion status.
     * @return
     */
    public String taskStatus() {
        if (this.isDone()) {
            return "D 1"
                    + this.getTask_details()
                    + " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                    + deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) + " )";
        } else {
            return "D 0"
                    + this.getTask_details()
                    + " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                    + deadlineTime.format(DateTimeFormatter.ofPattern("HHmm")) + " )";
        }
    }
}
