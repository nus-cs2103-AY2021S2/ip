package duke.command;

import duke.DukeException;
import duke.Messages;
import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert tasks != null;
        assert storage != null;

        return Messages.getListTaskMessage(tasks);
    }
}
