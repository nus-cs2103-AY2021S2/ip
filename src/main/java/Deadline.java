import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    /**
     * Date Getter.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Date Getter.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Print the new format of date and time.
     */
    public String printDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(this.date, this.time, ZoneId.of("Asia/Singapore"));
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(zonedDateTime);
    }

    /**
     * Return a new Deadline object after Task is executed.
     */
    @Override
    public Task doTask() {
        return new Deadline(this.job, true, this.date, this.time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + "(by:" + printDateTime() + ")";
    }
}
