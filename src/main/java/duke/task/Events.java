package duke.task;
import java.time.LocalDate;
import java.time.LocalTime;

public class Events extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Events(String title, Boolean b, LocalDate data) {
        super(title, b);
        this.date = data;
    }

    public Events(String title,Boolean b, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(title,b);
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
