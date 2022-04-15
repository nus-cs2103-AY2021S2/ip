package helper.command;

import helper.DukeException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

public class ClearCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.saveFile(tasks);
        return "Cleared the task list! Please save to store changes.";
    }
}
