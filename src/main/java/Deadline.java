import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private final LocalDate time;

    Deadline(String name, String time) throws DateTimeParseException {
        super(name);
        this.time = LocalDate.parse(time);
    }

    LocalDate getTime() {
        return this.time;
    }

    String getSymbol() {
        return "D";
    }

    @Override
    public String toString() {
        LocalDate date = this.getTime();
        return String.format("%s (at: %s %s %s)", super.toString(), date.getDayOfMonth(), date.getMonth(), date.getYear());

    }
}
