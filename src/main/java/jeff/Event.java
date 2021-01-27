package jeff;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDate date;
    private final LocalTime time;

    public Event(String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String getSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s %s %s %s:%s)", super.toString(), date.getDayOfMonth(), date.getMonth(), date.getYear(), time.getHour(), time.getMinute());

    }
}