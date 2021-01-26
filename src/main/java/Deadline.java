import java.time.LocalDate;
import java.time.LocalTime;
public class Deadline extends Task{
    private final String type;
    private final LocalDate date;
    private final LocalTime time;
    Deadline(String job, LocalDate date, LocalTime time) {
        super(job);
        this.type = "D";
        this.date = date;
        this.time = time;
    }

    Deadline(String job, Boolean done, LocalDate date, LocalTime time) {
        super(job, done);
        this.type = "D";
        this.date = date;
        this.time = time;
    }

    @Override
    public Task doTask() {
        return new Deadline(this.job, true, this.date, this.time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by:" + this.date + " " + this.time + ")";
    }
}
