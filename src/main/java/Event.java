package main.java;

import java.util.Date;

/**
 * Event inherits from Task. It encapsulates an event with an additional String at.
 * Event is specified by [E].
 */
public class Event extends Task {
    protected Date at;
    public Event (String description, Date at) {
        super(description);
        this.at = at;
    }

    public Date getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
