public class Event extends Task {

    /**
     * Stores the date this event will be at.
     */
    protected String at;

    /**
     * Initializes a newly created event-task object with a description and the date.
     * @param description Description of the task
     * @param at Date of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts this object to a string that represents the event-task
     * @return A string representing whether the event-task is done and the event-task description with the date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + at + ")";
    }
}
