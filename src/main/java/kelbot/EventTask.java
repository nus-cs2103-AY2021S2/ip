package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EventTask extends Task {
    protected LocalDate date;
    /**
     * Initializes Event Task.
     * @param name The name of the Event Task.
     * @param date The date that the Event Task must be completed by.
     */
    public EventTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " at " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
