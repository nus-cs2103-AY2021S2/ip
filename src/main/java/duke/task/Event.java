package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

/** Create a Event class
 *
 */
public class Event extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**Constructor to create events object
     * @param title
     * @param  b boolean
     * @param data duedate
     */

    public Event(String title, Boolean b, LocalDate data) {
        super(title, b);
        this.date = data;
    }

    /**Constructor to create events object for retrieval of task from data file
     * @param title
     * @param  b boolean
     * @param date duedate
     * @param  startTime
     * @param  endTime
     */

    public Event(String title, Boolean b, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(title, b);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /** Change representation of task to be added to data file
     * @return String
     */
    @Override
    public String changeFormat() {
        return "E" + super.changeFormat() + "," + this.date + "," + this.startTime + "," + this.endTime;
    }

    /** Print customized representation of task to user
     * @return String
     */
    @Override
    public String toString() {
        return " Event:" + super.toString() + "(at: " + this.date + " " + this.startTime + " " + this.endTime + ")";
    }
}
