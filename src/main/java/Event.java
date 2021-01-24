import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate time;

    public Event(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
        this.cat = 'E';
    }
    public LocalDate getTime() {
        return this.time;
    }

    public String getFormattedTime() {
        return this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.getFormattedTime() + ")" ;
    }
}
