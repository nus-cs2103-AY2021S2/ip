import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event type of tasks.
 */
public class Events extends Task {
    LocalDate at;

    public Events(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Events(String description, LocalDate at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns String format of event
     *
     * @return String format of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns String of error message for empty description
     *
     * @return String of error message for empty description
     */
    @Override
    public String getEmptyDescError() {
        return "Oops! Description of event " + super.getEmptyDescError();
    }

    /**
     * Formats data for saving into text file.
     *
     * @return E | isDone | description | at
     */
    @Override
    public String formatData() {
        return "E | " + super.formatData() + " | " + at;
    }
}
