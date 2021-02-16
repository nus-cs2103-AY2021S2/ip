package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The deadline class initialize and manage deadline tasks.
 */
public class Deadline extends Task {

    private LocalDate dueDate;
    private LocalTime dueTime;

    /**
     * Create a deadline task with description, due date and due time
     * @param description task description
     * @param dueBy due date
     * @param dueTime due time
     */
    public Deadline(String description, LocalDate dueBy, LocalTime dueTime) {
        super(description);
        this.dueDate = dueBy;
        this.dueTime = dueTime;
    }

    /**
     * Create a deadline task with additional status field
     * @param description task description
     * @param isCompleted task status
     * @param dueBy due date
     * @param dueTime due time
     */
    public Deadline(String description, Boolean isCompleted, LocalDate dueBy, LocalTime dueTime) {
        super(description, isCompleted);
        this.dueDate = dueBy;
        this.dueTime = dueTime;
    }

    /**
     * Returns a string representation of deadline task to be added to data file
     * @return string representation of deadline task
     */
    @Override
    public String changeFormat() {
        return "D" + super.changeFormat() + "," + this.dueDate + "," + this.dueTime;
    }

    /**
     * Returns a customized representation of deadline task to user
     * @return string representation of deadline task to be displayed to the user
     */
    @Override
    public String toString() {
        return " Deadline:" + super.toString() + " (by: " + this.dueDate + " " + this.dueTime + ")";
    }
}
