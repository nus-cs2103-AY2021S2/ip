package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        super.setExit(true);
        return ui.showBye();
    }
}
