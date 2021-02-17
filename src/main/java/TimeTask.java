import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type Time task.
 */
public class TimeTask implements Comparable<TimeTask> {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Task task;

    /**
     * Instantiates a new Time task.
     *
     * @param deadline the deadline
     */
    public TimeTask(Deadlines deadline) {
        this.date = deadline.getDateObj();
        this.startTime = LocalTime.of(0, 0);
        this.endTime = LocalTime.of(0, 0);
        this.task = deadline;
    }

    /**
     * Instantiates a new Time task.
     *
     * @param event the event
     */
    public TimeTask(Events event) {
        this.date = event.getDateObj();
        this.startTime = event.getStartTimeObj();
        this.endTime = event.getEndTimeObj();
        this.task = event;
    }

    /**
     * Gets task.
     *
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return this.task.toString();
    }

    @Override
    public int compareTo(TimeTask o) {
        if (this.date.isBefore(o.getDate())) {
            return 1;
        } else if (this.date.isEqual(o.getDate())) {
            if (this.startTime.isBefore(o.getStartTime())) {
                return 1;
            } else if (this.startTime.equals(o.getStartTime())) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
