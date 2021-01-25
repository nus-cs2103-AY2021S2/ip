package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.DukeException;
import duke.storage.Storage;

public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        try {
            Task task = Storage.getTasks().get(Integer.parseInt(command[1]) - 1);
            TaskList.markDone(task);
            Ui.displayDone(task);
            Storage.updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
