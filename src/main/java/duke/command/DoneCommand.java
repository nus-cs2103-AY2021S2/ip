package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class DoneCommand extends Command {

    public DoneCommand(String[] command) {
        super.command = command;
    }

    @Override
    public void process() throws DukeException {
        TaskList.markDone(command);
    }
}
