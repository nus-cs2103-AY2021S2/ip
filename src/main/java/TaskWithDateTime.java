import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class TaskWithDateTime extends Task {
    protected LocalDateTime dateTime;
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h.mma");

    public TaskWithDateTime(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }
}
