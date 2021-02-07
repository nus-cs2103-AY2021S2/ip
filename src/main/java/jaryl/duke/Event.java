package jaryl.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event represents a task with a task description and task date
 */
public class Event extends Task {
    private final LocalDateTime date;

    /**
     * Constructor to instantiate a new Event task
     * @param description   description of task
     * @param date          date of task
     * @throws InvalidFormatException   invalid format exception
     * @throws DateTimeParseException   date/time parse exception
     */
    public Event(String description, String date) throws InvalidFormatException, DateTimeParseException {
        super(description, "E");

        if(date.equals("")) {
            throw new InvalidFormatException("Please specify both task description and date/time using /at");
        }

        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Reformats string format of Event task when writing to disk
     * @return Event task in reformatted String format
     */
    public String fileFormat() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "E | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " + df.format(this.date);
    }

    /**
     * Converts Event task to string format
     * @return Event task in original String format
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[" + super.getType() + "]" + super.toString() + " (at: " + df.format(this.date) + ")";
    }
}
