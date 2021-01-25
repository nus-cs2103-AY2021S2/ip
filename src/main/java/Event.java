/**
 * Represents a task of type Event.
 * An Event is represented by a name and time of the event,
 * both in the form of a String.
 */
public class Event extends Task {

    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
