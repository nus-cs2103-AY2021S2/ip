package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a type of Task and represents the
 * abstraction of an event which occurs at a certain date.
 */
public class Event extends Task {
    protected LocalDate time;

    /**
     * Constructs a new Event with a description
     * that occurs a certain date.
     * @param description The specified description.
     * @param time The specified date.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Converts an event to the format to be saved to a file.
     * @return The event in save format.
     */
    @Override
    public String toSaveFormat() {
        String status = super.isDone ? "1" : "0";
        return String.format("E|%s|%s\n", status, super.description, this.time);
    }

    /**
     * Converts an event to the format to be displayed to the user by the Ui.
     * @return The event in display format.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.getStatusIcon(),
                this.description,
                this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
