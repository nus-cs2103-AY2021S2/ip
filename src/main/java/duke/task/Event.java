package duke.task;

import duke.tag.Tag;

import java.util.ArrayList;

/**
 * Represents an event task with a date
 */
public class Event extends Task {
    protected String at;

    public Event(String task, String at, ArrayList<Tag> tags) {
        super(task, tags);
        this.at = at;
    }

    public Event(boolean done, String task, String at, ArrayList<Tag> tags) {
        super(task, tags);
        this.at = at;
        this.isDone = done;
    }

    /**
     * Returns a String representation of this Event for storage to a file.
     * @return String representation
     */
    public String toFileString() {
        return "E | " + this.isDone + " | " + this.task + " | " + this.at
                + " | " + Tag.tagListToString(this.tags);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + this.at + ")";
    }
}
