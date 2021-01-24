package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    public DeleteCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        TaskList.deleteTask(command);
    }
}
