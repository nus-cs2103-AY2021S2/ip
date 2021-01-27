/**
 * Represents a Deadline.
 * Sub-class of Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Returns a Deadline.
     *
     * @param msg description of Deadline.
     * @return Deadline
     */
    Deadline(String msg, String by) {
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
    Deadline(String msg, Boolean isDone, String by) {
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

    /**
     * Returns a String that describes Deadline.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
