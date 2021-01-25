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

    public Event(boolean isDone, String description, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getDescription() {
        return "Event`" + this.isDone + "`" + this.description + "`" + this.at;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (" + at + ")";
    }
}
