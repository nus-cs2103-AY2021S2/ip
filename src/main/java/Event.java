import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDate event;

    public Event(String str) {
        super(str);//super must be 1st line..
        String[] split = str.split("/");
        if(split.length < 2) {
            throw new IllegalArgumentException();
        }
        this.task = split[0].trim();
        this.event = LocalDate.parse(split[1].trim());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" "+ "(at: "
                + event.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
