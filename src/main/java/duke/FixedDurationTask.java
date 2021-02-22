package duke;

/**
 * Represents a task that takes a fixed amount of time to complete but does not have a fixed
 * start/end time.
 */
public class FixedDurationTask extends Task {
    private final String duration;

    /**
     * Creates a new FixedDurationTask object with a description and duration.
     *
     * @param description Description of the task.
     * @param duration    Duration needed to complete the task.
     */
    public FixedDurationTask(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[FD]" + super.toString() + " (time required: " + duration + ")";
    }

    @Override
    public String getTaskCommand() {
        return "fixedDur " + getDescription() + " /dur " + duration;
    }
}
