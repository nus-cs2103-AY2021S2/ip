package kelbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {
    protected LocalDate date;
    /**
     * Initializes Deadline Task.
     * @param name The name of the Deadline Task.
     * @param date The date that the Deadline Task must be completed by.
     */
    public DeadlineTask(String name, LocalDate date) {
        super(name);
        this.date = date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "[D] " + super.toString() + " by " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
