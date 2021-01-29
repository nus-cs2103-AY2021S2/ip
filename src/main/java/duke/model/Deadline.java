package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate dateTime;

    public Deadline(boolean isCompleted, String taskName, LocalDate dateTime) {
        super('D', isCompleted, taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String generateFileFormatString() {
        return super.generateFileFormatString() + " // "
                + this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
