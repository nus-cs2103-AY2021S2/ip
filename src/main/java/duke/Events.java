import java.time.LocalDate;
import java.time.LocalTime;

public class Events extends Task {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Events(String title, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(title);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() + "(at:" + this.date + " from " + this.startTime + " to"
                + " " + this.endTime + ")";
    }
}
