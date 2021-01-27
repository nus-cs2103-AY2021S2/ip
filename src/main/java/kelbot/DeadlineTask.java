package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {
    
    protected LocalDate date;
    
    /*
     * Initializes Deadline Task
     */
    
    public DeadlineTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}