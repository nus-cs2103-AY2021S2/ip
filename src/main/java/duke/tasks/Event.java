package duke.tasks;

import java.time.LocalDate;

import duke.util.DateFormatter;

/**
 * Represents a Task as an Event.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param at Event date of the Task.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.taskType = "Event";
    }

    /**
     * Constructor for Deadline.
     * @param description Description of the Task.
     * @param isDone Marks whether the Task is Done.
     * @param at Event date of the Task.
     */
    public Event(String description, Boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
        this.taskType = "Event";
    }

    /**
     * Returns the date of the Event.
     * @return A LocalDate representing the Event date.
     */
    public LocalDate getAt() {
        assert this.at != null: "at should not be null!";
        return this.at;
    }

    /**
     * Returns a String representation of the Task.
     * @return A String representation of the Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateFormatter.decodeDate(at) + ")";
    }
}
