import java.time.LocalDate;
import java.time.LocalTime;


public class Event extends Task{
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    Event(String name, Boolean isDone,LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        super(name,isDone);
        this.startTime = startTime;
        this.startDate = startDate;
        this.endTime = endTime;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    Event finish(){
        return new Event(this.getName(), true,this.getStartDate(),this.getStartTime(),
                this.getEndDate(),this.getEndTime()) ;
    }
    @Override
    public String toString(){
        if(this.getIsDone()){
            return "[E][X] " + this.name + " | " + this.getStartDate()+ " " + this.getStartTime() +
                    " ~ " + this.getEndDate()+ " " + this.getEndTime();
        }
        else{
            return "[E][_] " + this.name + " | " + this.getStartDate()+ " " + this.getStartTime() +
                    " ~ " + this.getEndDate()+ " " + this.getEndTime();
        }
    }
}

