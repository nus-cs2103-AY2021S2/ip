package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor of an event
     *
     * @param name Name of the event
     * @param date Date of the event
     * @param time Time of the event
     */
    public Event(String name, LocalDate date, LocalTime time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor of an event
     *
     * @param name Name of the event
     * @param isDone Status of the Event
     * @param date Date of the event
     * @param time Time of the event
     */
    public Event(String name, boolean isDone, LocalDate date, LocalTime time) {
        super(name, isDone);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public LocalDateTime getDateTime() {
        return LocalDateTime.of(date, time);
    }

    @Override
    public String getType() {
        return "E";
    }

    /**
     * Prints the details of the event, including the name, time, and date
     *
     * @return A string representing the event
     */
    @Override
    public String toString() {
        String head = "[E][ ] ";
        if (this.isDone) {
            head = "[E][X] ";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return head + this.name + " (by: " + this.date.format(dateFormatter)
                + " " + this.time.format(timeFormatter) + ")";
    }
}
