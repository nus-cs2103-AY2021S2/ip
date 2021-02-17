import java.time.LocalDate;
import java.time.LocalTime;

public class TimeTask implements Comparable<TimeTask>{

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Task task;

    public TimeTask(Deadlines deadline){
        this.date = deadline.getDateObj();
        this.startTime = LocalTime.of(0,0);
        this.endTime = LocalTime.of(0,0);
        this.task = deadline;
    }

    public TimeTask(Events event){
        this.date = event.getDateObj();
        this.startTime = event.getStartTimeObj();
        this.endTime = event.getEndTimeObj();
        this.task = event;
    }

    public Task getTask() {
        return task;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString(){
        return this.task.toString();
    }

    @Override
    public int compareTo(TimeTask o) {
        //compare date, if date is same compare time
        //earlier -> greater
        //later -> lesser
        if(this.date.isBefore(o.getDate())){
            //earlier date
            return 1;
        }
        else if(this.date.isEqual(o.getDate())){
            //same date
            //compare start time
            if(this.startTime.isBefore(o.getStartTime())){
                return 1;
            } else if(this.startTime.equals(o.getStartTime())){
                return 0;
            } else{
                return -1;
            }
        }
        else{
            //later date
            return -1;
        }
    }
}
