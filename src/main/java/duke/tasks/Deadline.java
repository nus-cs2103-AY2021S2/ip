package duke.tasks;

import java.time.LocalDate;

import duke.Parser;

/**
 * Represents the subclass of Task. It contains the description of the task and a LocalDate that
 * represents the deadline of the Task.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructs a Deadline Task that contains the description of the task and its deadline.
     * Defaults with the task being not done.
     * @param description description of the task.
     * @param deadline deadline for the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns string representation of encoded data for the task so it can be saved.
     * @return string representation of the data of the task.
     */
    @Override
    public String data() {
        return String.format("D | %s | %s", super.data(), deadline);
    }

    /**
     * Returns string representation the task.
     * @return type of the task, whether the task is done, task's description, and task's deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Parser.localDateToString(deadline));
    }
}
