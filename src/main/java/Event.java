import java.time.LocalDate;
import java.time.LocalTime;


public class Event extends Task{
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    /**
     * Constructor of Event class.
     *
     * @param name name of event.
     * @param isDone event status.
     * @param startDate date of start event.
     * @param startTime time of start event.
     * @param endDate date of end event.
     * @param endTime time of end event.
     */

    Event(String name, Boolean isDone,LocalDate startDate, LocalTime startTime
            , LocalDate endDate, LocalTime endTime) {
        super(name,isDone);
        this.startTime = startTime;
        this.startDate = startDate;
        this.endTime = endTime;
        this.endDate = endDate;
    }

    /**
     * Get the start date.
     * @return the start date.
     *
     */

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Get the start time.
     * @return the start time.
     *
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Get the end date.
     * @return the end date.
     */

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Get the end time.
     * @return the end time.
     */

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Mark task as done
     *
     * @return new task, marked as done.
     */
    @Override
    Event finish() {
        return new Event(this.getName(), true,this.getStartDate(),this.getStartTime(),
                this.getEndDate(),this.getEndTime()) ;
    }
    @Override
    public String toString() {
        if(this.getIsDone()) {
            return "[E][X] " + this.name + " | " + this.getStartDate()+ " " + this.getStartTime() +
                    " ~ " + this.getEndDate()+ " " + this.getEndTime();
        }
        else{
            return "[E][_] " + this.name + " | " + this.getStartDate()+ " " + this.getStartTime() +
                    " ~ " + this.getEndDate()+ " " + this.getEndTime();
        }
    }
}

