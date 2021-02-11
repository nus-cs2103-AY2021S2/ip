package duke.commands;

import java.time.LocalDateTime;

import duke.models.Deadline;
import duke.models.Task;

/**
 * Handles the Deadline command of the user to create new deadlines in the list.
 * Format of command: "deadline [deadline_name] /by [deadline]".
 */
public class DeadlineCommand extends AddCommand {
    private LocalDateTime deadline;

    /**
     * Command relating to creating a new Deadline
     * @param taskName name of the deadline
     * @param deadline deadline of the specified task
     */
    public DeadlineCommand(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public Task getTask() {
        return new Deadline(getTaskName(), deadline);
    }
}
