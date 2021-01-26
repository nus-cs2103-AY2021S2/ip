import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String printDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(this.date, this.time, ZoneId.of("Asia/Singapore"));
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
    }

    @Override
    public Event doTask() {
        return new Event(this.job, true, this.date, this.time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(at:" + printDateTime() + ")";
    }
}
