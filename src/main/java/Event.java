import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate time;
    public Event(String task, String time) {
        super(task);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
