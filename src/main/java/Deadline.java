import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final LocalDate date;
    private final LocalTime time;

    Deadline(String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    LocalDate getDate() {
        return this.date;
    }

    LocalTime getTime() {
        return this.time;
    }

    String getSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s %s %s %s:%s)", super.toString(), date.getDayOfMonth(), date.getMonth(), date.getYear(), time.getHour(), time.getMinute());
    }
}
