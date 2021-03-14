/**
 * Represents a Task Object that is an event at a certain location.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for this Event object.
     *
     * @param   description  Task Description.
     * @param   EventLocation           Location of task.
     */
    public Event(String description, String EventLocation) {
        super(description);
        this.at = EventLocation;
    }

    /**
     * Returns a string representation of the Event Task.
     *
     * @return the type of task, task completion status, and location.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
}
