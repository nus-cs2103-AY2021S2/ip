package duke.command;

import duke.DukeException;
import duke.data.Data;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        try {
            Task task = Data.tasks.get(Integer.parseInt(command[1]) - 1);
            TaskList.markDone(task);
        } catch (Exception e) {
            throw new DukeException("That doesn't seem like a valid order number...");
        }
    }
}
