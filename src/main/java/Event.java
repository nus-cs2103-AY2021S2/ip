import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime localDate;

    public Event(String input, String date) {
        super(input);
        this.localDate = new ParseDates().parseString(date);
    }

    public Event(String input, String date, int done) {
        super(input);
        this.localDate = new ParseDates().parseString(date);
        if (done == 1) {
            this.doTask();
        }
    }

    @Override
    public String taskSave() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "E" + super.taskSave() + " | " + localDate.format(dateTimeFormatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + localDate.format(dateTimeFormatter) + ")";
    }
}
