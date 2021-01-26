import java.time.LocalDate;
import java.time.LocalTime;
public class Event extends Task{
    private final String type;
    private final LocalDate date;
    private final LocalTime time;
    Event(String job, LocalDate date, LocalTime time) {
        super(job);
        this.type = "E";
        this.date = date;
        this.time = time;
    }

    Event(String job, Boolean done, LocalDate date, LocalTime time) {
        super(job, done);
        this.type = "E";
        this.date = date;
        this.time = time;
    }

    @Override
    public Event doTask() {
        return new Event(this.job, true, this.date, this.time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(at:" + this.date + " " + this.time + ")";
    }
}
