package duke.command;

import duke.DukeException;
import duke.Messages;
import duke.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        storage.saveTasksToFile(tasks);
        return Messages.MESSAGE_EXIT;
    }
}
