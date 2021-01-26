package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate date;
    protected String duration;


    Event(String description, boolean isCompleted, String time) {
        super(description, isCompleted);
        String[] dateDuration = time.split(" ");
        String eventDate = dateDuration[0];
        this.date = LocalDate.parse(eventDate);
        this.duration = dateDuration[1];
    }

    public LocalDate getTaskDate() {
        return this.date;
    }

    @Override
    public String getFormattedData() {
        return  "E | " + super.getFormattedData() + "| " + date + " " + duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                duration + ")";
    }
}
