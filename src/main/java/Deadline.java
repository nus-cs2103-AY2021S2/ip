import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task object that is define by a due date
 */
public class Deadline extends Task implements DueDate {
    protected LocalDate time;

    /**
     * Constructs a deadline that has yet to be completed
     * @param description description of the deadline
     * @param time due date in yyyy-MM-dd format (e.g. 2021-03-12)
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a deadline
     * @param description description of the task
     * @param isDone whether the task is completed (true indicated that the task is completed,
     *              while false indicates that the task is incomplete
     * @param time due date in yyyy-MM-dd format (e.g. 2021-03-12)
     */
    public Deadline(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Marks a deadline as completed
     * @return a task that is completed
     */
    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.time);
    }

    /**
     * Method to know if the deadline has been completed or not
     * @return true if the task is completed and false if it is not
     */
    @Override
    public String getDueDate() {
        return this.time.toString();
    }

    /**
     * Returns information on the deadline
     * @return A String that describes the deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + time.format(formatter) + ")";
    }
}
