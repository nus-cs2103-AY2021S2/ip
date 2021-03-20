package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() {

    }
    public String execute(TaskList tasks, Storage storage) {
        return Ui.showHelpMessage();
    }
}
