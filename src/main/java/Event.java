import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * class that represents the Event Task.
 * Inherits from the superclass Task.
 */
public class Event extends Task {

    private char type;
    private LocalDate at;

    /**
     * Constructor to create an Event Task.
     * Keeps track of the date the event is at.
     * @param task Name of the Event Task.
     * @param at Date of the Event Task.
     */
    public Event(String task, String at) {
        super(task);
        at = at.replaceFirst(" ", "");
        this.at = LocalDate.parse(at);
        this.type = 'E';
    }

    /**
     * Constructor used when retrieving data from hard drive.
     * @param task Name of the Event.
     * @param at Date of the Event.
     * @param isDone If the Event is done or not.
     */
    public Event(String task, boolean isDone, String at) {
        super(task, isDone);
        this.type = 'E';
        at = at.replaceFirst(" ", "");
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString()
                + "(at:" + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getData() {
        return "[" + type + "]" + super.toString()
                + " (at:" + at + ")";
    }
}
