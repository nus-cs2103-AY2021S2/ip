/**
 * A type of Task that will happen at some point in the future.
 */
public class Event extends Task {
    protected String at;
    public Event(String description,String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
