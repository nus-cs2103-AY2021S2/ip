package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate date;
    private final String timeRange;

    public Event(String description, LocalDate date, String timeRange) {
        super(description);
        this.date = date;
        this.timeRange = timeRange;
    }

    public Event(String description, LocalDate date, String timeRange, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.timeRange = timeRange;
    }

    @Override
    public Event markAsDone() {
        return new Event(description, date, timeRange, true);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timeRange + ")";
    }
}
