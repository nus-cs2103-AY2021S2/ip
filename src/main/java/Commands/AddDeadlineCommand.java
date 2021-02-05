package Commands;

import Tasks.Deadline;
import Tasks.TaskList;
import UserInterface.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime byDateTime;

    public AddDeadlineCommand(String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui) {
        Deadline deadline = new Deadline(this.description, this.byDateTime);
        tasks.addTask(deadline);
        ui.handleAddTask(tasks, deadline);
    }
}
