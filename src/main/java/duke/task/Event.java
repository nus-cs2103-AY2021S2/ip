package duke.task;

/**
 * Represents an event task with a date
 */
public class Event extends Task {
    protected String at;
    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    public Event(boolean done, String task, String at) {
        super(task);
        this.at = at;
    }

    /**
     * Returns a String representation of this Event for storage to a file.
     * @return String representation
     */
    public String fileString() {
        return "E | " + this.done + " | " + this.task + " | " + this.at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (At: " + this.at + ")";
    }
}
