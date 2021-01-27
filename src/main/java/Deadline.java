import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task{
    private LocalDate DeadlineDate;
    private LocalTime DeadlineTime;
    Deadline(String name,Boolean isDone, LocalDate DeadlineDate, LocalTime DeadlineTime){
        super(name,isDone);
        this.DeadlineDate = DeadlineDate;
        this.DeadlineTime = DeadlineTime;
    }
    public LocalDate getDeadlineDate(){
        return this.DeadlineDate;
    }
    public LocalTime getDeadlineTime() {
        return DeadlineTime;
    }

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
