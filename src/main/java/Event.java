import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    LocalDate time;
    
    public Event(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    public Event(String name, boolean isDone, String time) {
        super(name, isDone);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("dd MMM yyy")));
    }
}