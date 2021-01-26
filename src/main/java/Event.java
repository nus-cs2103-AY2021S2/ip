import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Event extends Task{
    protected boolean isDone;
    protected LocalDateTime time;
    protected final static String type = "[E]";

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
        this.isDone = false;
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (at: " + getTime() + ")";
    }

}
