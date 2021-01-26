/**
 * A type of Task that will happen at some point in the future.
 */
public class Event extends Task {
    protected String at;
    public Event(String description,String at) {
        super(description, "E");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[" + this.getLetterCode() + "]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getSavedStringFormat() {
        return super.getSavedStringFormat() + " | " + this.at;
    }
}
