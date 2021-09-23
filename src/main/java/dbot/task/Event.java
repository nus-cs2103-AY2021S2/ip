package dbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An implementation of the Task class that represents an Event Task.
 *
 * Event tasks are tasks that take a description and an /at parameter which specifies the date of the Event.
 *
 * The Event class is visually represented with the prefix: [E]
 */
public class Event extends Task {
    /** The Date on which the event is held at. */
    protected LocalDate at;

    /**
     * Initializes an Event Task with the provided description String and date.
     *
     * @param description A String containing the Event description.
     * @param at A LocalDate that represents the date of the Event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the exact String required to construct a replicate of this Event Task.
     *
     * @return A String that can be used to construct a replicate Event Task.
     */
    @Override
    public String getFullDescription() {
        return getDescription() + " /at " + at;
    }

    /**
     * Returns this Event Task with its representative prefix: [E].
     *
     * @return A String representing this Event Task with the prefix [E].
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
