package DukeObjects;

/**
 * Represents a task with a duration specified in the at argument.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates a task with a duration specified in the by argument.
     *
     * @param description Describes the task.
     * @param at          Specifies the duration of this task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
