import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    protected LocalDateTime dateTime;

    protected TimedTask(String description, LocalDateTime dateTime, String icon) {
        super(description, icon);
        this.dateTime = dateTime;
    }

    public void changeDate(LocalDate newDate) {
        this.dateTime = LocalDateTime.of(newDate, this.dateTime.toLocalTime());
    }

    public void changeTime(LocalTime newTime) {
        this.dateTime = LocalDateTime.of(this.dateTime.toLocalDate(), newTime);
    }
}
