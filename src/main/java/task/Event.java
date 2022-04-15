package task;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.DukeException;

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

    public Event(String description, LocalDate date, String timeRange, boolean isDone, List<String> tags) {
        super(description, isDone, tags);
        this.date = date;
        this.timeRange = timeRange;
    }

    /**
     * Formats a Event for storing in the file.
     *
     * @return a String representation of the Event
     */
    public String fileFormat() {
        return "E | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | "
                + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + timeRange + " | " + tags;
    }

    /**
     * Marks a Event as done.
     * @throws DukeException if the Event is already marked as done.
     * @return a new Event that is considered done
     */
    @Override
    public Event markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This Event has already been marked as done!");
        }
        return new Event(description, date, timeRange, true, tags);
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
