import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    public Event(String name, LocalDate at) throws DukeException {
        super(name);
        this.at = at;
    }

    @Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (at:" + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
