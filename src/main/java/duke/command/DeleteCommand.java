package duke.command;

import duke.DukeException;
import duke.data.Data;
import duke.task.TaskList;

import static duke.data.Data.updateDataFile;

public class DeleteCommand extends Command {

    public DeleteCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        try {
            int id = Integer.parseInt(command[1]) - 1;
            TaskList.deleteTask(id, Data.getTasks());
            updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
