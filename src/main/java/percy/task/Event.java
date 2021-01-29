package percy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    public static final String PREFIX = "E";
    protected LocalDate date;
    protected LocalTime time;
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }


    @Override
    public String formatToFile() {
        return PREFIX + " | " + super.formatToFile() + " | "
                + date + " "
                + time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return "[" + PREFIX + "]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }
}
