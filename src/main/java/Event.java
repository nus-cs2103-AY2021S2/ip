/**
 * This Event class extends the Task class and is used to
 * represent a single event that users can add to their list.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (" + at + ")";
    }
}
