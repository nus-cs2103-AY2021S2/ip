package duke.tasks;

/**
 * Represents task with a deadline.
 */
public class Deadline extends Task {
    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description, "[D]");
        this.deadline = deadline;
    }

    /**
     * Returns deadline of task.
     * @return task deadline
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
