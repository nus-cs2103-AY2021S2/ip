import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate period;

    public Deadline(String description, String period) throws DukeException {
        super(description);
        try {
            this.period = LocalDate.parse(period);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please specify the date in this format:\n"
                                        + "  deadline [task description] /at [yyyy-mm-dd]");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + period.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}