package duke;

import java.time.LocalDate;

/**
 * Represents an event
 */
public class Event extends Task {
    private LocalDate date;
    private String time;

    /**
     * Constructor of an event
     * @param name Name of the event
     * @param date Date of the event
     * @param time Time of the event
     */
    public Event(String name, LocalDate date, String time) {
        super(name);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructor of an event
     * @param name Name of the event
     * @param done Status of the Event
     * @param date Date of the event
     * @param time Time of the event
     */
    public Event(String name, boolean done, LocalDate date, String time) {
        super(name, done);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    /**
     * Prints the details of the event, including the name, time, and date.
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        String head = "[E][ ] ";
        if (this.done) {
            head = "[E][X] ";
        }
        return head + this.name + " (at: " + this.date.getMonth() + " "
                + this.date.getDayOfMonth() + " " + this.date.getYear() + " "
                + this.time + ")";
    }
}
