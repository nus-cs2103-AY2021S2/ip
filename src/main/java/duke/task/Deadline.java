package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

/** Create a Deadline class
 *
 */
public class Deadline extends Task {

    private LocalDate dueDate;
    private LocalTime dueTime;

    /**Constructor to create deadline object
     * @param title
     * @param dueBy
     * @param time
     */
    public Deadline(String title, LocalDate dueBy, LocalTime time) {
        super(title);
        this.dueDate = dueBy;
        this.dueTime = time;
    }

    /** Overloaded constructor for retrieval of task from data file
     * @param title
     * @param b boolean
     * @param dueBy
     * @param time
     */
    public Deadline(String title, Boolean b, LocalDate dueBy, LocalTime time) {
        super(title, b);
        this.dueDate = dueBy;
        this.dueTime = time;
    }

    /** Change representation of task to be added to data file
     * @return String
     */
    @Override
    public String changeFormat() {
        return "D" + super.changeFormat() + "," + this.dueDate + "," + this.dueTime;
    }

    /** Print customized representation of task to user
     * @return String
     */
    @Override
    public String toString() {
        return " Deadline:" + super.toString() + " (by: " + this.dueDate + " " + this.dueTime + ")";
    }
}
