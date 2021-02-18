package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event class initialize and manage event tasks.
 */
public class Event extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Create an event task with description, task status and due date
     * @param description task description
     * @param isCompleted task status
     * @param dueDate due date
     */

    public Event(String description, Boolean isCompleted, LocalDate dueDate) {
        super(description, isCompleted);
        this.date = dueDate;
    }

    /**
     * Create an event task with additional start and end time field
     * @param description task description
     * @param isCompleted task status
     * @param dueDate due date
     * @param startTime start time
     * @param endTime end time
     */

    public Event(String description, Boolean isCompleted, LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        super(description, isCompleted);
        this.date = dueDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of event task to be added to data file
     * @return string representation of event task
     */
    @Override
    public String changeFormat() {
        return "E" + super.changeFormat() + "," + this.date + "," + this.startTime + "," + this.endTime;
    }

    /**
     * Returns a customized representation of task to user
     * @return string representation of event task to be displayed to the user
     */
    @Override
    public String toString() {
        return " Event:" + super.toString() + "(at: " + this.date + " " + this.startTime + " " + this.endTime + ")";
    }
}
