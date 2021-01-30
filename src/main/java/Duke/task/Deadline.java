package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline.
 * Sub-class of Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Deadline extends Task{
    protected LocalDateTime by;

    /**
     * Returns a Deadline.
     *
     * @param msg description of Deadline.
     * @return Deadline
     */
    Deadline(String msg, LocalDateTime by) {
        super(msg);
        this.by = by;
    }

    /**
     * Returns a Deadline.
     *
     * @param msg description of Deadline.
     * @param isDone boolean that tracks whether Deadline is completed.
     * @param by time of deadline.
     * @return Deadline
     */
    Deadline(String msg, Boolean isDone, LocalDateTime by) {
        super(msg, isDone);
        this.by = by;
    }

    /**
     * Returns a Deadline that set boolean isDone as true.
     *
     * @return Deadline marked as done.
     */
    @Override
    public Deadline setDone() {
        return new Deadline(this.msg, true, this.by);
    }

    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "D" + "|" + super.encode() + "|" + by.format(formatter);
    }


    /**
     * Returns a String that describes Deadline.
     *
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
