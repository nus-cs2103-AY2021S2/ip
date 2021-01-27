package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EventTask extends Task {
    
    protected LocalDate date;
    
    public EventTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at:" + this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}