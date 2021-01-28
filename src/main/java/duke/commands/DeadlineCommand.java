package duke.commands;

import duke.models.Deadline;
import duke.models.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddCommand {
    private LocalDateTime deadline;

    public DeadlineCommand(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public Task getTask() {
        return new Deadline(getTaskName(), deadline);
    }
}
