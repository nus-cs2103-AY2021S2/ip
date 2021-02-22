package jeff.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline, child class of Task.
 * Contains the date as a LocalDate and time as a LocalTime.
 */
public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor for Deadline.
     *
     * @param name Name of Deadline.
     * @param date Date of Deadline.
     * @param time Time of Deadline.
     * @throws DateTimeParseException If there is an error parsing the date or time.
     */
    public Deadline(String name, String date, String time) throws DateTimeParseException {
        super (name);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns date of Deadline.
     *
     * @return Date of Deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns time of Deadline.
     *
     * @return Time of Deadline.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns symbol representing Deadline (i.e. "D").
     *
     * @return "D".
     */
    public String getSymbol() {
        return "D";
    }

    /**
     * Returns String representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s %s %s %s)", super.toString(), date.getDayOfMonth(),
                date.getMonth(), date.getYear(), time);
    }
}
