package duke;

/**
 * A subclass of Task, used for tasks with specified timing.
 */
public class Event extends Task {
    protected static final String TAG = "[E]";

    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return Event.TAG + super.toString();
    }
}
