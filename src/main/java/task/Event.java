package task;

public class Event extends Task{
    protected String at;
    /**
     * Instantiates a new event task.
     *
     * @param description the event description
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "EVENT \u00BB " + super.description + " (at: " + at + ") " + super.getStatusIcon();
    }
}
