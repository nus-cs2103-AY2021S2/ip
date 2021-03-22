import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Class that handles the Deadline task.
 * Inherits from superclass Task.
 */
public class Deadline extends Task {

    private LocalDate by;
    private char type;

    /**
     * Constructor to create a Deadline Task.
     * Keeps track of the date the Deadline is by.
     * @param task Name of the Deadline Task.
     * @param by Date the Deadline is due by.
     */
    public Deadline(String task, String by) throws DukeException {
        super(task);
        by = by.replaceFirst(" ", "");
        this.by = LocalDate.parse(by);
        this.type = 'D';
    }

    /**
     * Constructor used when retrieving data from hard drive.
     * @param task Name of the Deadline.
     * @param by Date that the Deadline is due by.
     * @param isDone If the Deadline is done or not.
     */
    public Deadline(String task, boolean isDone, String by) throws DukeException {
        super(task, isDone);
        this.type = 'D';
        by = by.replaceFirst(" ", "");
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[" + type + "]"  + super.toString()
                + "(by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getData() {
        return "[" + type + "]"  + super.toString()
                + " (by:" + by + ")";
    }
}
