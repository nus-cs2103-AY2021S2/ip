package jaryl.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline represents a task with a task description and deadline date
 */
public class Deadline extends Task {
    private final LocalDateTime date;

    /**
     * Constructor to instantiate a new Deadline task
     * @param description   description of task
     * @param date          deadline date of task
     * @throws InvalidFormatException   invalid format exception
     * @throws DateTimeParseException   date/time parse exception
     */
    public Deadline(String description, String date) throws InvalidFormatException, DateTimeParseException {
        super(description, "D");

        if(date.equals("")) {
            throw new InvalidFormatException("Please specify both task description and date/time using /by");
        }
        this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Reformats string format of Deadline task when writing to disk
     * @return Deadline task in reformatted String format
     */
    public String fileFormat() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "D | " + (super.isDone ? "1 | " : "0 | ") + this.description + " | " + df.format(this.date);
    }

    /**
     * Converts Deadline task to string format
     * @return Deadline task in original String format
     */
    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return "[" + super.getType() + "]" + super.toString() + " (by: " + df.format(this.date) + ")";
    }
}
