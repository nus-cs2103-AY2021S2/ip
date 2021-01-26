/**
 * A subclass of <code>Task</code>that has an extra variable to keep track of date and overrides toString() method of
 * <code>Task</code>
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
