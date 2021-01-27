import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private LocalDateTime date;

    public EventTask(String info, String date) {
        super(info);
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getDate());
    }

}
