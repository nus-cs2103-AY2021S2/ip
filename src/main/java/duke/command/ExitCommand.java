package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showExit();
    }
}
