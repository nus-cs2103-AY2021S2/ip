package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    private Deadline deadline;

    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.getAddMessage(tasks, tasks.addTask(deadline));
        storage.save(tasks);
        return message;
    }
}
