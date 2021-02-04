import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
    Deadline(String name, String deadLine) {
        super(name);
        this.deadline = LocalDate.parse(deadLine, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM D YYYY")) + ")";
    }

    @Override
    public String toStorageString() {
        return "D|" + super.toStorageString() + "|" + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
    }
}
