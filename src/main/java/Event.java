/**
 * Represents an Event.
 * Sub-class of Task.
 *
 * @author Oh Jun Ming
 * @version 1.0
 */
public class Event extends Task{
    protected String time;

    /**
     * Returns an Event.
     *
     * @param msg description of Event.
     * @param time time of Event.
     * @return Event
     */
    Event(String msg, String time) {
        super(msg);
        this.time = time;
    }

    /**
     * Returns an Event.
     *
     * @param msg description of Event.
     * @param isDone boolean that tracks whether Event is completed.
     * @param time time of Event.
     * @return Event
     */
    Event(String msg, Boolean isDone, String time) {
        super(msg, isDone);
        this.time = time;
    }

    /**
     * Returns an Event that set boolean isDone as true.
     *
     * @return Event marked as done.
     */
    @Override
    public Event setDone() {
        return new Event(this.msg, true, this.time);
    }

    public String encode() {
        return "E" + "|" + super.encode() + "|" + this.time;
    }

    /**
     * Returns a String that describes Event.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }
}
