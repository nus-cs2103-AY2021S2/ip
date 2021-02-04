import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate timing;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");

    Event(String name, String timing) {
        super(name);
        this.timing = LocalDate.parse(timing, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.timing.format(DateTimeFormatter.ofPattern("MMM D YYYY")) + ")";
    }

    @Override
    public String toStorageString() {
        return "E|" + super.toStorageString() + "|" + this.timing.format(DateTimeFormatter.ofPattern("yyyy-M-dd"));
    }
}
