<<<<<<< HEAD
public class Event extends Task {
    private String date;
=======
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime localDate;
>>>>>>> branch-Level-8

    public Event(String input, String date) {
        super(input);
        LocalDateTime lDate = new ParseDates().parseString(date);
        this.localDate = lDate;
    }

    public Event(String input, String date, int done) {
        super(input);
        this.date = date;
        if (done == 1) {
            this.doTask();
        }
    }

    @Override
    public String taskSave() {
        return "E" + super.taskSave() + " | " + date;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + localDate.format(dateTimeFormatter) + ")";
    }
}
