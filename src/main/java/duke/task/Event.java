package duke.task;

/**
 * Represents a task with a duration specified in the "at" argument.
 */
public class Event extends Task {
    protected String inputAfterAt;

    /**
     * Creates a task with a duration specified in the "at" argument.
     *
     * @param description  Describes the task.
     * @param inputAfterAt Specifies the duration of this task.
     */
    public Event(String description, String inputAfterAt) {
        super(description);
        this.inputAfterAt = inputAfterAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + inputAfterAt + ")";
    }
}
