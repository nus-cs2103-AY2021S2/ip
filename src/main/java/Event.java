public class Event extends Task {
    protected String at;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the Event task.
     * @param at Location of the Event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s\n", Command.EVENT, super.toFileString(), this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
