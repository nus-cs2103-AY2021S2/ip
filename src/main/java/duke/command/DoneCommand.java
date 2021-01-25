package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    protected String fullCommand;

    public DoneCommand(String fullCommand) {
        super();
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.done(this.fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}