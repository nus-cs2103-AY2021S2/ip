package duke.command;

import duke.DukeException;
import duke.data.Data;
import duke.task.Task;
import duke.task.TaskList;

import static duke.data.Data.updateDataFile;

public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        try {
            Task task = Data.getTasks().get(Integer.parseInt(command[1]) - 1);
            TaskList.markDone(task);
            updateDataFile();
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
