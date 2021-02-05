package Commands;

import Tasks.Deadline;
import Tasks.TaskList;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends AddCommand {
    private final String description;
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    public void execute(TaskList tasks) {
        Deadline deadline = new Deadline(this.description, this.by);
        this.executeAddTask(tasks, deadline);
    }
}
