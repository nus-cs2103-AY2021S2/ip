package duke;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

/**
 * A Task that has both a time and date.
 */
public class Event extends Task {
    private final LocalDate date;
    private final String timeRange;

    /**
     * Creates a Event
     *
     * @param description name of the Event
     * @param date the date of the Event
     * @param timeRange the time of the Event
     */
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

    /**
     * Formats a Event for storing in the file.
     *
     * @return a String representation of the Event
     */
    public String fileFormat() {
        return "E | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " +
                date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + timeRange;
    }

    /**
     * Marks a Event as done.
     *
     * @return a new Event that is considered done
     */
    @Override
    public Event markAsDone() {
        return new Event(description, date, timeRange, true);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + timeRange + ")";
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTimeRange() {
        return timeRange;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof Event && description.equals(((Event) obj).getDescription())
                    && date.equals(((Event) obj).getDate()) && timeRange.equals(((Event) obj).getTimeRange())
                            && isDone == ((Event) obj).isDone();
        }
    }
}
