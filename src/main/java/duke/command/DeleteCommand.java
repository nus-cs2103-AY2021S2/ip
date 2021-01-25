package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.DukeException;
import duke.storage.Storage;

public class DeleteCommand extends Command {

    public DeleteCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        try {
            int id = Integer.parseInt(command[1]) - 1;
            Task task = TaskList.deleteTask(id, Storage.getTasks());
            Ui.displayRemovedTask(task, Storage.getTasks());
            Storage.updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
