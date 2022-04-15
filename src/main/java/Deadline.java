import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private final LocalDate DeadlineDate;
    private final LocalTime DeadlineTime;

    /**
     * Constructor of Deadline class.
     *
     * @param name name of deadline.
     * @param isDone deadline status.
     * @param DeadlineDate date of deadline
     * @param DeadlineTime time of deadline
     *
     */
    Deadline(String name, Boolean isDone
            , LocalDate DeadlineDate, LocalTime DeadlineTime) {
        super(name,isDone);
        this.DeadlineDate = DeadlineDate;
        this.DeadlineTime = DeadlineTime;
    }

    /**
     * Get deadline date
     *
     * @return deadline date.
     */

    public LocalDate getDeadlineDate(){
        return this.DeadlineDate;
    }

    /**
     * Get deadline time
     *
     * @return deadline time.
     */

    public LocalTime getDeadlineTime() {
        return DeadlineTime;
    }

    /**
     * Mark task as done
     *
     * @return new task, marked as done.
     */
    @Override
    Deadline finish(){
        return new Deadline(this.getName(), true, this.getDeadlineDate(), this.getDeadlineTime()) ;
    }

    @Override
    public String toString(){
        if(this.getIsDone()){
            return "[D][X] " + this.getName() +" | "+ this.getDeadlineDate()+ " "  + this.getDeadlineTime();
        }
        else{
            return "[D][_] " + this.getName() +" | " +this.getDeadlineDate()+ " " + this.getDeadlineTime();

        }
    }
}
