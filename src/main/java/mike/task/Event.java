package mike.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    protected final String time;
    protected LocalDate date;

    public Event(String description, String time) {
        super(description);
        this.time = time;
        if (Character.isDigit(time.charAt(0))) {
            date = LocalDate.parse(time);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
            this.date = LocalDate.parse(time, formatter);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
