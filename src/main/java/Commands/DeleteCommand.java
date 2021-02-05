package Commands;

import Tasks.Task;
import Tasks.TaskList;
import UserInterface.Ui;

public class DeleteCommand extends Command {
    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui) {
        Task deletedTask = tasks.popTaskByIndex(Integer.parseInt(description));
        if (null != deletedTask) {
            ui.handleDelete(deletedTask);
        }
    }

    public boolean isExit() {
        return false;
    }
}
