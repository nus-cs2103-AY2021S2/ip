/**
 * {@code Event} is a {@code Task} that has a specific time of occurrence.
 * @see Task
 */
public class Event extends Task {
    private final String at;

    /**
     * Constructs a new uncompleted {@code Event}.
     *
     * @param description The name of the task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * String representation of the Event.
     *
     * @return Event in check list form.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
