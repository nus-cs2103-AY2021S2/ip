import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate period;

    public Event(String description, String period) throws DukeException {
        super(description);
        try {
            this.period = LocalDate.parse(period);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please specify the date in this format:\n"
                                        + "  event [task description] /at [yyyy-mm-dd]");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
