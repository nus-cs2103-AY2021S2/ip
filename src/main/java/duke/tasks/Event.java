package duke.tasks;

/**
 * Inherited from Task, used to store information related to tasks of type 'Event'.
 *
 * Events are tasks that start at a specific time and ends at a specific time
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event class object.
     * @param description event description
     * @param at starting and ending time
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        assert !at.isEmpty() : "event cannot be created without an at date!";
    }

    /**
     * Constructor for Event class object, used for storage.
     * @param description event description
     * @param at starting and ending time
     */
    public Event(String description, boolean isDone, String at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
        assert !at.isEmpty() : "event cannot be created without an at date!";
    }

    /**
     * Acts as a helper method to print out the details of the event.
     * @return the details of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Helper method to get the details of the event to be stored.
     * @returnthe the details of the event to be stored
     */
    @Override
    public String infoToStore() {
        assert !description.isEmpty() : "event does not have a description!";
        assert !at.isEmpty() : "event does not have an at date!";

        String divider = " | ";
        return "E" + divider
                + (isDone ? "1" : "0") + divider
                + description + divider
                + at;
    }
}
