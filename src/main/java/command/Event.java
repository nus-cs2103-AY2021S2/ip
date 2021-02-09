package command;

import classes.Task;

import java.util.Date;

/**
 * Event inherits from Task. It encapsulates an event with an additional String at.
 * Event is specified by [E].
 */
public class Event extends Task {
    protected Date at;

    /**
     * Constructor method.
     * @param description User input description of event.
     * @param at Date and time at which the event is to take place.
     */
    public Event (String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Method to retrieve the date of the event.
     * @return a Date object of the event.
     */
    public Date getAt() {
        return this.at;
    }

    /**
     * Method to return a formatted version of user's input.
     * @return Formatted String of user's input.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
