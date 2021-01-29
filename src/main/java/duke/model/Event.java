package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate dateTime;

    public Event(boolean isCompleted, String taskName, LocalDate dateTime) {
        super('E', isCompleted, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String generateFileFormatString() {
        return super.generateFileFormatString() + " // "
                + this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
