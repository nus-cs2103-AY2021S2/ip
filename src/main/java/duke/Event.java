package duke;

/**
 * A child of <code>Task</code> object, corresponds to an event task with
 * description supplied by the user. eg., <code>alan's birthday /at 6 Aug 6-8pm</code>
 * @see Task
 */
public class Event extends Task {
    protected String timing;

    public Event(String description, String location) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + timing;
    }
}
