import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    LocalDate at;

    public Events(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Events(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getEmptyDescError() {
        return "Oops! Description of event " + super.getEmptyDescError();
    }

    @Override
    public String formatData() {
        return "E | " + super.formatData() + " | " + at;
    }
}
