import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime localDate;

    public Event(String input, String date) {
        super(input);
        LocalDateTime lDate = new ParseDates().parseString(date);
        this.localDate = lDate;
    }

    public Event(String input, String date, int done) {
        super(input);
        LocalDateTime lDate = new ParseDates().parseString(date);
        this.localDate = lDate;
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
