package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event object.
 */
public class Event extends Task {
    private LocalDate datetime;

    public Event(String description, LocalDate time) {
        super(description);
        this.datetime = time;
    }

    /**
     * Returns a String representing this object to be saved into a save data file.
     * @return String representation of this object, formatted for save data use.
     */
    public String getSaveString() {
        String datetimeString = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (this.isDone()) {
            return String.format("[isDone] event %s /on %s\n", description, datetimeString);
        } else {
            return String.format("event %s /on %s\n", description, datetimeString);
        }
    }

    @Override
    public String toString() {
        String datetimeString = datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        assert datetimeString.length() > 0 : "Datetime string not successfully initialized";
        return String.format("[E][%s] %s (on: %s)", getStatus(), description, datetimeString);
    }
}