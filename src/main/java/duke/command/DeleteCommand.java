package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    protected String fullCommand;

    public DeleteCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(this.fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
