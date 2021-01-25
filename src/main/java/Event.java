import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate date;
    protected String duration;

    Event(String description, String time) {
        super(description);
        String[] dateDuration = time.split(" ");
        String eventDate = dateDuration[0];
        this.date = LocalDate.parse(eventDate);
        this.duration = dateDuration[1];
    }

    public LocalDate getTaskDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                duration + ")";
    }
}
