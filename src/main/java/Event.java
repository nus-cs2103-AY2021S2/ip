import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(int isDone, String description, LocalDate at) {
        super('E', isDone, description);
        this.at = at;
    }

    @Override
    public String getFileString() {
        return super.getFileString() + " // " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
