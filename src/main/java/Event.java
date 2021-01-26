import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime localDate;

    public Event(String input, String date) {
        super(input);
        LocalDateTime lDate = new ParseDates().parseString(date);
        this.localDate = lDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + localDate.format(dateTimeFormatter) + ")";
    }
}
