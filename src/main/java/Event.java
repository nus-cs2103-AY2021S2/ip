import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private LocalDate timing;

    Event(String name, String timing) throws DukeTimingException {
        super(name);
        List<String> formatterStrings = Arrays.asList("yyyy-M-dd", "yyyy M dd", "yyyy/M/dd",
                "M dd YYYY");
        for (String formatterString : formatterStrings) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterString);
                this.timing = LocalDate.parse(timing, formatter);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }
        if (this.timing == null) {
            throw new DukeTimingException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.timing.format(DateTimeFormatter.ofPattern("MMM dd YYYY")) + ")";
    }

    @Override
    public String toStorageString() {
        return "E|" + super.toStorageString() + this.timing.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
    }
}
