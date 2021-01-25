package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String title, Boolean isCompleted, LocalDate data) {
        super(title, isCompleted);
        this.date = data;
    }

    public Event(String title, Boolean isCompleted, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(title,isCompleted);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String changeFormat(){
        return "E" + super.changeFormat() + "," + this.date + "," + this.startTime + "," + this.endTime;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + "(at: " + this.date + " " + this.startTime + " " + this.endTime + ")";
    }
}
