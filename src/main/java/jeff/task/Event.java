package jeff.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event, child class of Task.
 * Contains the date as a LocalDate and time as a LocalTime.
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor for Event.
     *
     * @param name Name of Event.
     * @param date Date of Event.
     * @param time Time of Event.
     * @throws DateTimeParseException If there is an error parsing the date or time.
     */
    public Event(String name, String date, String time) throws DateTimeParseException {
        super (name);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns date of Event.
     *
     * @return Date of Event.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns time of Event.
     *
     * @return Time of Event.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns symbol representing Event (i.e. "E").
     *
     * @return "E".
     */
    public String getSymbol() {
        return "E";
    }

    /**
     * Returns String representation of Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s %s %s %s)", super.toString(), date.getDayOfMonth(),
                date.getMonth(), date.getYear(), time);
    }
}
