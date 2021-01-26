import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    public LocalDate at;

    public Event(String task, String at) {
        super(task);
        at = at.replaceFirst(" ", "");
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]"  + super.toString()
                + "(at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
