import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a timed task.
 */
public abstract class TimedTask extends Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
    protected LocalDateTime dateTime;

    protected TimedTask(String description, LocalDateTime dateTime, String icon) {
        super(description, icon);
        this.dateTime = dateTime;
    }

    protected TimedTask(String description, LocalDateTime timeCreated, LocalDateTime dateTime, String icon) {
        super(description, timeCreated, icon);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void changeDate(LocalDate newDate) {
        this.dateTime = LocalDateTime.of(newDate, this.dateTime.toLocalTime());
    }

    public void changeTime(LocalTime newTime) {
        this.dateTime = LocalDateTime.of(this.dateTime.toLocalDate(), newTime);
    }

    public String toLog() {
        return super.toLog() + " | " + dateTime;
    }

    @Override
    public boolean isTimed() {
        return true;
    }
}
