import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private LocalDate deadline;

    Deadline(String name, String deadLine) throws DukeTimingException {
        super(name);
        List<String> formatterStrings = Arrays.asList("yyyy-M-dd", "yyyy M dd", "yyyy/M/dd",
                "M dd YYYY");
        for (String formatterString : formatterStrings) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatterString);
                this.deadline = LocalDate.parse(deadLine, formatter);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }
        if (this.deadline == null) {
            throw new DukeTimingException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd, YYYY")) + ")";
    }

    @Override
    public String toStorageString() {
        return "D|" + super.toStorageString()
                + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
    }
}
