package duke;

/**
 * The Event class has methods for a Event object
 * Inherits from the Task.
 */

public class Event extends Task {

    private String at;

    /**
     * Constructor for new Event
     *
     * @param description of the new Event
     * @param at from new Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String representation for Event object
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.at + ")";

    }

}
