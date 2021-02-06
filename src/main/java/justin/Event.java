package justin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Event extends Task {

    protected String dateTime;
    protected String date;
    protected String time;
    protected LocalDate date1;
    protected LocalTime time1;

    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
        String[] splits = dateTime.split("\\s+");
        this.date = splits[0];
        this.time = splits[1];
        this.date1 = LocalDate.parse(date);
        this.time1 = LocalTime.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time1.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }
}