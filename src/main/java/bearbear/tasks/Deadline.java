package bearbear.tasks;

/**
 * Represents task with a deadline.
 */
public class Deadline extends Task {
    private final String deadline;

    /**
     * Creates a {@code Deadline} object with a task description and task deadline component.
     * @param description Task description.
     * @param deadline Task deadline.
     */
    public Deadline(String description, String deadline) {
        super(description, "[D]");
        this.deadline = deadline;
    }

    /**
     * Returns deadline of task.
     * @return Task deadline.
     */
    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        String str = deadline.strip();
        return super.toString() + "(by: " + str + ")";
    }
}
