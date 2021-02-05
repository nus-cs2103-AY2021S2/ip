package Commands;

import Tasks.Deadline;
import Tasks.TaskList;
import UserInterface.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui) {
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.addTask(deadline);
        ui.handleAddTask(tasks, deadline);
    }
}
