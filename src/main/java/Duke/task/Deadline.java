package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline.
 * Sub-class of Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Deadline extends Task {
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
     * Returns time deadline of Deadline.
     *
     * @return
     */
    public LocalDateTime getTime() {
        return by;
    }
    /**
     * @param task
     * @return
     */
    @Override
    public boolean equals(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            boolean a = this.isDone.equals(deadline.getDone());
            boolean b = this.msg.equals(deadline.getMsg());
            boolean c = this.getTime().equals(deadline.getTime());
            return a && b && c;
        } else {
            return false;
        }
    }
    /**
     * Returns a Deadline that set boolean isDone as true.
     *
     * @return Deadline marked as done.
     */
    @Override
    public Deadline setDone() {
        System.out.println("deadline set done");

        return new Deadline(this.msg, true, this.by);
    }

    /**
     * Encodes Task to string format for storage.
     *
     * @return String
     */
    @Override
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
