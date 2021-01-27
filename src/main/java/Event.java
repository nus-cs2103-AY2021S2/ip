import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime at;
    private final String type = "E";

    public Event(String description, String at) {
       this(description, at, false);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        setTime(at);
    }

    private void setTime(String time) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
        LocalDateTime dateTime = LocalDateTime.parse(time, inputFormat);
        this.at = dateTime;
    }

    @Override
    public String toString() {
        String time = this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")).replace("T", " ");
        return type + separator + super.toString() + separator + time;
    }
}
